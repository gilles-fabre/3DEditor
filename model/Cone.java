package model;

import java.util.Iterator;
import java.util.Vector;

import trigonometry.TrigoTable;

public class Cone extends Model {
	
	public static final int CLASS_ID = 4;
	public int getClassId() {return CLASS_ID;}
	
	static final int MAX_ANGLE = 360;
	static final int ANGLE_INCREMENT = MAX_ANGLE / 20;

	public void create(int _radius, int _height, int angle_increment) {
		double radius = _radius;
		double height = _height;
		
		Vector polygon = new Vector();		// create the polygon on the fly
		
		for (int i = 0; i <= MAX_ANGLE; i += angle_increment) {
			double x = TrigoTable.cos(i) * radius;
			double y = TrigoTable.sin(i) * radius;
			Point p = new Point(x, y, 0);
			polygon.add(p); 
		}
		
		Point []points = new Point[polygon.size()];
		points = (Point[])polygon.toArray(points);
		addPolygon(points);
	
		Point p = new Point(0, 0, height);

		Point lastPoint = null;
		Iterator i = polygon.iterator();
		while (i.hasNext()) {
			Point nextPoint = (Point)i.next();
			
			if (lastPoint != null) {
				Point []poly = new Point[4];
				poly[0] = lastPoint;
				poly[1] = p;
				poly[2] = p;
				poly[3] = nextPoint;
				addPolygon(poly);
			}

			lastPoint = nextPoint;
		}
	}
	
	public Cone(int diameter, int height, int angle_increment) {
		create(diameter / 4, height, angle_increment);
	}

	public Cone(int diameter, int height) {
		create(diameter / 4, height, ANGLE_INCREMENT);
	}

	public Cone() {
		create(INITIAL_OBJECT_SIZE / 2, INITIAL_OBJECT_SIZE, ANGLE_INCREMENT);
	}

	public Model factory() {
    	Model model = new Cone();
    	return model.copy(this);
    }
}
