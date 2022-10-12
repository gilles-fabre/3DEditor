package model;

import java.util.Vector;

import trigonometry.TrigoTable;

import model.Model;

public class Sphere extends Model {
	public static final int CLASS_ID = 5;
	public int getClassId() {return CLASS_ID;}

	// the poles shall not be duplicated!
	protected Point []partiallyDuplicatePoints(Point []array) {
		Point []newArray = new Point[array.length];
		newArray[0] = array[0];
		int i = 1;
		do {
			newArray[i] = new Point(array[i]);
		} while (++i < array.length - 1);
		newArray[i] = array[i];
		
		return newArray;
	}

	private void generatePolygons(Point []array1, Point []array2) {
		// pole polygons have only 3 points
		Point []polygon = new Point[4];
		polygon[3] = array2[1];
		polygon[2] = array2[0];
		polygon[1] = array1[0];
		polygon[0] = array1[1];
		addPolygon(polygon);
		
		for (int i = 1; i < array1.length - 2; i++) {
			polygon = new Point[4];
			polygon[3] = array1[i];
			polygon[2] = array1[i + 1];
			polygon[1] = array2[i + 1];
			polygon[0] = array2[i];
			addPolygon(polygon);
		}
		
		// pole polygons have only 3 points
		polygon = new Point[4];
		polygon[3] = array1[array1.length - 2];
		polygon[2] = array1[array1.length - 1];
		polygon[1] = array2[array1.length - 1];
		polygon[0] = array2[array1.length - 2];
		addPolygon(polygon);
		
	}
	private void create(int _radius, int angleIncrement) {
		Vector arc = new Vector();		
		double radius = _radius;

		//	create the intial arc
		for (int i = 0; i <= MAX_ANGLE / 2; i += angleIncrement) {
			double x = TrigoTable.cos(i) * radius;
			double y = TrigoTable.sin(i) * radius;
			Point p = new Point(x, y, 0);
			arc.add(p); 
		}
		
		Point []arcPoints = new Point[arc.size()];
		arcPoints = ((Point[])arc.toArray(arcPoints));

		/* iterate MAX_ANGLE / angleIncrement times on:
		 	next <- copy arc
		 	rotate next around x by angleIncrement
		 	connect next and arc points on the same parallel
		 	arc <- next
		*/	
		int i = 0;
		do {
			Point []nextArcPoints = partiallyDuplicatePoints(arcPoints);
			rotatePoints(nextArcPoints, angleIncrement, 0, 0);
			generatePolygons(nextArcPoints, arcPoints);
			arcPoints = nextArcPoints;
			i += angleIncrement;
		} while (i < MAX_ANGLE);
	}

	public Sphere() {
		create(INITIAL_OBJECT_SIZE / 4, ANGLE_INCREMENT);
	}
	
	public Sphere(int diameter) {
		create(diameter / 4, ANGLE_INCREMENT);
	}

	public Model factory() {
    	Model model = new Sphere();
    	return model.copy(this);
    }
}
