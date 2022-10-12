package model;

import java.util.Vector;

import trigonometry.TrigoTable;

public class Cylinder extends Model {
	public static final int CLASS_ID = 8;
	public int getClassId() {return CLASS_ID;}
	
	static final int DISK_SPACE = 5;
	
	public Cylinder() {
		create(INITIAL_OBJECT_SIZE / 4, INITIAL_OBJECT_SIZE);
	}
	
	public Cylinder(int diameter, int height) {
		create(diameter / 4, height);
	}

	public void create(int _radius, int _height) {
		double	radius = _radius,
				height = _height;
		
		Vector polygon = new Vector();		// create the polygon on the fly
		double halfHeight = height / 2;

		// create the back and fore disks
		for (int i = 0; i <= MAX_ANGLE; i += ANGLE_INCREMENT) {
			double x = TrigoTable.cos(i) * radius;
			double y = TrigoTable.sin(i) * radius;
			Point p = new Point(x, y, -halfHeight);
			polygon.add(p); 
		}
		
		Point []points = new Point[polygon.size()];
		points = (Point[])polygon.toArray(points);
		Point []opposite = duplicatePoints(points);
		rotatePoints(opposite, 180, 0, 0);
		addPolygon(points);
		addPolygon(opposite);
		
		// add the polygons around the cylinder
		int i = 0,
			j = opposite.length - 1;
		do {
			Point []poly = new Point[4];
			poly[0] = points[i];
			poly[1] = opposite[j];
			poly[2] = opposite[j - 1];
			poly[3] = points[i + 1];
			addPolygon(poly);
			--j;
			++i;
		} while (i < points.length - 1);
	}

	public Model factory() {
    	Model model = new Cylinder();
    	return model.copy(this);
    }
}
