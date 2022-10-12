package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.TreeSet;

import model.Model;
import model.Point;
import editor.Editor;

public class CameraViewRenderer extends Renderer {
	int		xEyeDistance = - INITIAL_EYE_DISTANCE / 3,
			yEyeDistance = - INITIAL_EYE_DISTANCE / 3,
			zEyeDistance = INITIAL_EYE_DISTANCE * 2;

	public CameraViewRenderer() {
		name = "Camera View";
		mouseTip = "left mouse button (+shift) changes eye position on x and y (on z)";
    	setBackground(Color.BLACK);		
	}

	public class ColoredPolygon extends Polygon implements Comparable {
		Color 	color;
		double	depth;
		
		public ColoredPolygon(Color _color, double _depth) {
			super();
			color = _color;
			depth = _depth;
		}
		
		public Color getColor() {
			return color;
		}

		public int compareTo(Object arg0) {
			int result = (int) (depth - ((ColoredPolygon)arg0).depth);
			if (result == 0)
				result = 1; // no equal polygons!
			
			return result;
		}
		
		private void draw(Graphics g) {
			g.setColor(color.darker());
			g.drawPolygon(this);
			color.brighter();
		}
		
		private void fill(Graphics g) {
			g.setColor(color);
			g.fillPolygon(this);
		}

		public void paint(Graphics g) {
			fill(g);
			draw(g);
		}
	}
	
	private void drawPolygons(Graphics g, TreeSet visiblePolygons) {
		Iterator i = visiblePolygons.iterator();
		while (i.hasNext()) {
			ColoredPolygon poly = (ColoredPolygon)i.next();
			poly.paint(g);
		}
	}
	
	/**
	 * The camera view draws 'real world' models: hidden faces + painter algorithm.
	 * It goes through all models, sort visible faces according to their average
	 * depth, and paint them starting from the back most polygon. 
	 * 
	 */
	private void sortAndDrawModelPolygons(TreeSet visiblePolygons, Model model, int offX, int offY) {
	   	model.project(xEyeDistance, yEyeDistance, zEyeDistance);
	   	
		Point home = model.getHome();
		home.project(xEyeDistance, yEyeDistance, zEyeDistance);

		Iterator j = model.getPolygons().iterator();
        while (j.hasNext()) {
        	Point []poly = (Point [])j.next(); 
            ColoredPolygon poly2D = new ColoredPolygon(model.getColor(), getAverageDepth(poly) + home.getZ());
        	if (!isPolygonVisible(poly))
        		continue;

        	for (int p = 0; p < poly.length; p++) {
	        	Point pt = poly[p];
	        	poly2D.addPoint((int)(pt.getZX() + home.getZX()) + offX, 
	        				    (int)(pt.getZY() + home.getZY()) + offY);
	        }
			
        	// add polygon to the tree
			visiblePolygons.add(poly2D); 
        }

    	
		if (model.getChildren() != null) {
			Iterator c = model.getChildren().iterator();
			while (c.hasNext()) {
				sortAndDrawModelPolygons(visiblePolygons, (Model)c.next(), offX, offY);
			}
		}
	}
	
	public void update(Graphics g) {
		Dimension d = getSize();
		int offX = d.width >> 1;
		int offY = d.height >> 1;

		// use a TreeSet to store the polygons
		TreeSet visiblePolygons = new TreeSet(); 
		Iterator i = Editor.editor.getModels().iterator();
		while (i.hasNext()) {
			sortAndDrawModelPolygons(visiblePolygons, (Model)i.next(), offX, offY);
		}
		
		// draw polygons
		drawPolygons(g, visiblePolygons);
		
		if (name != null) {
			g.setColor(NAME_COLOR);
			g.drawString(name, NAME_X, NAME_Y);
		}

		g.setColor(EYE_DISTANCE_COLOR);
		String eyeDistances = "eye distance: " + new Integer(getXEyeDistance()).toString() + "," + new Integer(getYEyeDistance()).toString() + "," + new Integer(getZEyeDistance()).toString();
		g.drawString(eyeDistances, EYE_DISTANCE_X, EYE_DISTANCE_Y);

		// get the view size, draw a tip at the bottom left
		offX = 10;
		offY = d.height - NAME_X;
		g.setColor(MOUSE_TIP_COLOR);
		g.drawString(mouseTip, offX, offY);
	}

	public void mouseClicked(MouseEvent arg0) {
		switch (button) {
		case MouseEvent.BUTTON1: 
			scaleModels(1.1, 1.1, 1.1);
			break;

		case MouseEvent.BUTTON3: 
			scaleModels(0.9, 0.9, 0.9);
			break;
		}
	
		Editor.editor.getEditorGUI().getMainWindow().repaint();
	}

	public void mouseDragged(MouseEvent arg0) {
		if (mouseDown)
		{
			int x = arg0.getX(),
				y = arg0.getY();
			
			switch (button) {
				case MouseEvent.BUTTON1: 
					if (arg0.isShiftDown())
						zEyeDistance += y - yAnchor;
					else
						xEyeDistance += x - xAnchor;
						yEyeDistance += y - yAnchor;
					break;
			}
			
			xAnchor = x;
			yAnchor = y;
			Editor.editor.getEditorGUI().getMainWindow().repaint();
		}
	}

	protected int getXEyeDistance() {
		return xEyeDistance;
	}

	protected int getYEyeDistance() {
		return yEyeDistance;
	}

	protected int getZEyeDistance() {
		return zEyeDistance;
	}
}
