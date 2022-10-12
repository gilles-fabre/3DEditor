package model;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JSpinner;

public class Box extends Model {
	public static final int CLASS_ID = 2;
	public int getClassId() {return CLASS_ID;}

	public Box() {
		create(INITIAL_OBJECT_SIZE, INITIAL_OBJECT_SIZE, INITIAL_OBJECT_SIZE);
	}

	public Box(int width, int height, int depth) {
		create(width, height, depth);
	}

	public void create(int _width, int _height, int _depth) {
		double halfWidth = _width / 2,
			   halfHeight = _height / 2,
			   halfDepth = _depth / 2;
		Point p1 = new Point(-halfWidth, -halfHeight, -halfDepth),
			  p2 = new Point(halfWidth, -halfHeight, -halfDepth),
			  p3 = new Point(halfWidth, halfHeight, -halfDepth),
			  p4 = new Point(-halfWidth, halfHeight, -halfDepth),
			  p5 = new Point(-halfWidth, -halfHeight, halfDepth),
			  p6 = new Point(halfWidth, -halfHeight, halfDepth),
			  p7 = new Point(halfWidth, halfHeight, halfDepth),
			  p8 = new Point(-halfWidth, halfHeight, halfDepth);
	
		// create the polygons.
		// front
		Point []polygon1 = {p1, p2, p3, p4};
		addPolygon(polygon1);

		// right
		Point []polygon2 = {p2, p6, p7, p3};
		addPolygon(polygon2);

		// back
		Point []polygon3 = {p6, p5, p8, p7};
		addPolygon(polygon3);

		// left
		Point []polygon4 = {p5, p1, p4, p8};
		addPolygon(polygon4);

		// top
		Point []polygon5 = {p4, p3, p7, p8};
		addPolygon(polygon5);

		// bottom
		Point []polygon6 = {p5, p6, p2, p1};
		addPolygon(polygon6);
	}

	public Model factory() {
    	Model model = new Box();
    	return model.copy(this);
    }
}

