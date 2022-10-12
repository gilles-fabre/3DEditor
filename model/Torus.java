package model;

import java.util.Vector;
import trigonometry.TrigoTable;

public class Torus extends Model {
	public static final int CLASS_ID = 9;
	public int getClassId() {return CLASS_ID;}

	static final int MAX_ANGLE = 360;
	static final int ANGLE_INCREMENT = MAX_ANGLE / 20;

	private void connectDisks(Disk prev, Disk next) {
		// add the polygons around the disks
		int i = 0,
			j = next.getPoints().size() - 1;
		do {
			Point []poly = new Point[4];
			poly[0] = (Point)prev.getPoints().elementAt(i);
			poly[1] = (Point)next.getPoints().elementAt(j);
			poly[2] = (Point)next.getPoints().elementAt(j - 1);
			poly[3] = (Point)prev.getPoints().elementAt(i + 1);
			addPolygon(poly);
			--j;
			++i;
		} while (i < prev.getPoints().size() - 1);
	}
	
	private void create(int _radius, int angleIncrement) {
		// on a virtual (radius size) circle, create disks with center on the
		// vcircle. connect points on the disks by pairs. shift disks pair. 
		
		// create the vcircle.
		Vector vdisk = new Vector();		
		double radius = _radius;

		for (int i = 0; i <= MAX_ANGLE; i += angleIncrement) {
			double y = TrigoTable.sin(i) * radius;
			double z = TrigoTable.cos(i) * radius;
			Point p = new Point(0, y, z);
			vdisk.add(p); 
		}

		// now goes around vdisk, and create a pair of disks, and connect them
		int i = 0; 
		Disk first = null,
			 last = null;
		
		do { 
			Point diskCenter = (Point)vdisk.get(i);
			Disk disk = new Disk();
			if (first == null)
				first = disk;
			disk.rotatePoints(((i * angleIncrement) + 90) % 360, 0 ,0);
			disk.translatePoints(0, 
						    	  (int)diskCenter.getY(), 
						    	  (int)diskCenter.getZ());	
			// connect disks.
			if (last != null) {
				connectDisks(last, disk);
			}
			last = disk;
		} while (i++ < MAX_ANGLE / ANGLE_INCREMENT);

		connectDisks(last, first);
	}

	public Torus() {
		create(INITIAL_OBJECT_SIZE / 4, ANGLE_INCREMENT);
	}
	
	public Torus(int diameter) {
		create(diameter / 4, ANGLE_INCREMENT);
	}

	public Model factory() {
    	Model model = new Torus();
    	return model.copy(this);
    }
}
