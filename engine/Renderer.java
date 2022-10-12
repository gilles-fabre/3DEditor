package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import javax.swing.JPanel;

import model.Model;
import model.Point;
import editor.Editor;

public abstract class Renderer extends JPanel implements MouseListener, MouseMotionListener {
	static final int			NAME_X = 15, NAME_Y = 15; 
	static final int			EYE_DISTANCE_X = 100, EYE_DISTANCE_Y = 15; 
	static final int			NAME_RED = 0, NAME_GREEN = 0, NAME_BLUE = 255; 
	static final int			INITIAL_EYE_DISTANCE = 1000;
	static final Color			EYE_DISTANCE_COLOR = Color.BLUE;
	static final Color			NAME_COLOR = Color.GREEN;
	static final Color			MOUSE_TIP_COLOR = new Color(0, 128, 0);
	static int					xAnchor, yAnchor, button;
	static boolean				mouseDown = false;
	String						name, 		// view name
								mouseTip;	// help on mouse usage in view					
	
	protected double getAverageDepth(Point []polygon)	{
		double sum = 0;
		
		int i = 0;
		do {
			sum += polygon[i].getZ();
		} while (++i < polygon.length);
		
		return sum / i;
	}
	
	protected boolean isPolygonVisible(Point []polygon)	{
	  Point p1, p2, p3;

	  double midPoint = polygon.length;
	  midPoint /= 2;
	  
	  p1 = polygon[0];
	  p2 = polygon[(int)(midPoint - 1)];
	  p3 = polygon[polygon.length - 1];
	  
	  double a1, a2, b1, b2;
	  a1 = p1.getZX() - p2.getZX();
	  b1 = p1.getZY() - p2.getZY();
	  a2 = p3.getZX() - p2.getZX();
	  b2 = p3.getZY() - p2.getZY();

	  return ((a1*b2)-(b1*a2)) >= 0;
	}	
	
	void translateModels(int Dx, int Dy, int Dz) {
		Iterator i = Editor.editor.getSelectedModels().iterator();
		while (i.hasNext())
			((Model)i.next()).translate(Dx, Dy, Dz);
	}

	void rotateModels(int Rx, int Ry, int Rz) {
		Iterator i = Editor.editor.getSelectedModels().iterator();
		while (i.hasNext())
			((Model)i.next()).rotate(Rx, Ry, Rz);
	}

	void scaleModels(double Sx, double Sy, double Sz) {
		Iterator i = Editor.editor.getSelectedModels().iterator();
		while (i.hasNext())
			((Model)i.next()).scale(Sx, Sy, Sz);
	}

	void drawModel(Graphics g, Model model) {
        Graphics2D g2 = (Graphics2D) g;

        // #### this makes drawing slower and ugly 
        // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int	xEyeDistance = getXEyeDistance(),
			yEyeDistance = getYEyeDistance(),
			zEyeDistance = getZEyeDistance();

		// get the view size, and place the home at the center of the view...
		Dimension d = getSize();
		int offX = d.width >> 1;
		int offY = d.height >> 1;
		Point home = model.getHome();
		home.project(xEyeDistance, yEyeDistance, zEyeDistance);
		
		// set model color
		if (Editor.editor.getSelectedModels().contains(model)) 
			g.setColor(Color.RED);
		else
			g.setColor(model.getColor());

		// draw model home
    	g2.drawLine((int)home.getZX() + offX, 
					(int)home.getZY() + offY, 
					(int)home.getZX() + offX, 
					(int)home.getZY() + offY);

		model.project(xEyeDistance, yEyeDistance, zEyeDistance);
		Iterator j = model.getPolygons().iterator();
        while (j.hasNext()) {
        	Point []poly = (Point [])j.next(); 
            Polygon poly2D = new Polygon();
        	for (int p = 0; p < poly.length; p++) {
	        	Point pt = poly[p];
	        	poly2D.addPoint((int)(pt.getZX() + home.getZX()) + offX, 
	        				    (int)(pt.getZY() + home.getZY()) + offY);
	        }
			
        	// draw the polygon
			g.drawPolygon(poly2D); 
        }

    	
		if (model.getChildren() != null) {
			Iterator c = model.getChildren().iterator();
			while (c.hasNext()) {
				drawModel(g, (Model)c.next());
			}
		}
	}
	
	public Renderer() {
	   	super();
    	setBackground(Color.white);
        addMouseMotionListener(this);
        addMouseListener(this);
        name = "Unamed";
 	}

    public void update(Graphics g) {
		Iterator i = Editor.editor.getModels().iterator();
		while (i.hasNext()) {
			Model m = (Model)i.next();
			drawModel(g, m);
		}
		
		if (name != null) {
			g.setColor(NAME_COLOR);
			g.drawString(name, NAME_X, NAME_Y);
		}

		g.setColor(EYE_DISTANCE_COLOR);
		String eyeDistances = "eye distance: " + new Integer(getXEyeDistance()).toString() + "," + new Integer(getYEyeDistance()).toString() + "," + new Integer(getZEyeDistance()).toString();
		g.drawString(eyeDistances, EYE_DISTANCE_X, EYE_DISTANCE_Y);

		// get the view size, draw a tip at the bottom left
		Dimension d = getSize();
		int offX = 10;
		int offY = d.height - NAME_X;
		g.setColor(MOUSE_TIP_COLOR);
		g.drawString(mouseTip, offX, offY);
    }
	
	/** The component inside the scroll pane. */ 
	protected void paintComponent(Graphics g) 
	{ 
		super.paintComponent(g); 
		update(g);
	}

	public void mouseClicked(MouseEvent arg0) {
		Editor.editor.getEditorGUI().getMainWindow().updateAll(null);
	}

	public void mousePressed(MouseEvent arg0) {
		xAnchor = arg0.getX();
		yAnchor = arg0.getY();
		button = arg0.getButton();
		mouseDown = true;
	}

	public void mouseReleased(MouseEvent arg0) {
		mouseDown = false;
		Editor.editor.getEditorGUI().getMainWindow().updateAll(null);
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mouseDragged(MouseEvent arg0) {
	}

	public void mouseMoved(MouseEvent arg0) {
	}
	
	protected int getXEyeDistance() {
		return INITIAL_EYE_DISTANCE;
	}

	protected int getYEyeDistance() {
		return INITIAL_EYE_DISTANCE;
	}

	protected int getZEyeDistance() {
		return INITIAL_EYE_DISTANCE;
	}
}
