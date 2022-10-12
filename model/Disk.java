package model;

import java.util.Vector;

import trigonometry.TrigoTable;

public class Disk extends Model {
	public static final int CLASS_ID = 3;
	public int getClassId() {return CLASS_ID;}
	
	static final int MAX_ANGLE = 360;
	static final int ANGLE_INCREMENT = MAX_ANGLE / 20;

	private void create(int _radius, int angleIncrement) {
		Vector polygon = new Vector();		// create the polygon on the fly
		double radius = _radius;

		for (int i = 0; i <= MAX_ANGLE; i += angleIncrement) {
			double x = TrigoTable.cos(i) * radius;
			double y = TrigoTable.sin(i) * radius;
			Point p = new Point(x, y, 0);
			polygon.add(p); 
		}
		
		Point []points = new Point[polygon.size()];
		points = (Point[])polygon.toArray(points);
		Point []opposite = duplicatePoints(points);
		rotatePoints(opposite, 180, 0, 0);
		addPolygon(points);
		addPolygon(opposite);
	}

	public Disk() {
		create(INITIAL_OBJECT_SIZE / 4, ANGLE_INCREMENT);
	}

	public Disk(int diameter) {
		create(diameter / 4, ANGLE_INCREMENT);
	}

	public Model factory() {
    	Model model = new Disk();
    	return model.copy(this);
    }
}
