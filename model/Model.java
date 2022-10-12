/**
 * A 3D model. Knows how to scale/rotate and project itself. Can be saved and
 * Loaded. Holds a color to be drawn with.
 * 
 * Important note: Each model has its own home, thus enabling rotating each model
 * around its own home. But, when a model is parented, its home becomes the parent
 * home, such that rotating the parent makes the child rotate around the parent's 
 * home. Consequenty, translating or composing child models requires translating the
 * child actual points... 
 * 
 * @date March, 2006
 * @author gilles fabre
 */
package model;

import java.awt.Color;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import utilities.SimpleStringTokenizer;
import matrix.Matrix;

public class Model {
	public static final int CLASS_ID = 1;
	public int getClassId() {return CLASS_ID;}
	
	static  public final  int		INITIAL_OBJECT_SIZE = 100;

	static final int MAX_ANGLE = 360;
	static final int INITIAL_SCALING = 1;
	static final int ANGLE_INCREMENT = MAX_ANGLE / 20;

	Vector 		connections = new Vector();
	Vector		points;						// memoized list of points.
	Point		home = new Point(0, 0, 0);	// home for this model
	Color		color;						// model color
	String		name = new SimpleStringTokenizer(getClass().getName()).lastToken() + "_" + new Integer((int)(System.currentTimeMillis() % 999)).toString(); // model name
	Model		parent;						// is this embedded in a parent?
	Vector		children;					// has this model children?
	Vector		polygons = new Vector();	// polygons.
	Matrix		transformationMatrix = new Matrix(Matrix.noTransformMatrix);
	
    private void changeHome(Point newHome) {
    	Iterator i = getPoints().iterator();
    	Point delta = new Point(newHome);
    	delta.sub(home);
    	while (i.hasNext()) {
			Point p = (Point)i.next();
			p.sub(delta);
    	}		
    }
   
    /**
	 * 
	 * @return the Vector of Points for this
	 */
    Vector getPoints() {
    	if (points == null) {
	    	points = new Vector();
	    	Iterator i = getConnections().iterator();
	    	while (i.hasNext()) {
	    		Connection c = (Connection)i.next();
	    	    points.add(c.getOrigin());
	    	}
    	}
    	
    	return points;
    }

    public Model() {
    	color = Color.BLACK;
    }

    public Model(Color _color) {
    	color = _color;
    }
    
    // WARNING: this will lose all subclass attributes...
    public Model(Model m) {
    	copy(m);
    }

    /**
     * This factory creates an in depth copy of this
     */
    public Model factory() {
    	Model model = new Model();
    	return model.copy(this);
    }
    
    protected Model copy(Model m) {
    	color = new Color(m.getColor().getRed(), 
    					  m.getColor().getGreen(),
    					  m.getColor().getBlue());
    	name = new String(m.getName()) + "_copy";
    	setHome(new Point(m.home));

		// remove all polygons
    	getPolygons().clear();

    	// now copy them and just copy angle
		Iterator i = m.getPolygons().iterator();
		while (i.hasNext()) {
			addPolygon(duplicatePoints((Point[])i.next()));
		}
	    
		transformationMatrix = new Matrix(m.transformationMatrix);
		
		// copy children
		if (m.getChildren() != null) {
			Iterator child = m.getChildren().iterator();
			while (child.hasNext()) {
				Model c = (Model)child.next();
				addChild(c.factory());
			}
		}
		
		return this;
    }

    /**
    * 
    * @return the list of connections for this.
    */
    public Vector 	getConnections() {return connections;}
	
    protected void	translatePoints(int dX, int dY, int dZ) {
    	Iterator i = getPoints().iterator();
    	while (i.hasNext()) {
    		((Point)i.next()).translate(dX, dY, dZ);
    	}
    }
    
    protected void	rotatePoints(int rX, int rY, int rZ) {
    	Iterator i = getPoints().iterator();
    	while (i.hasNext()) {
    		((Point)i.next()).rotate(rX, rY, rZ);
    	}
    }

	/**
	 * Transform this by matrix m
	 * @param The m transformation matrix
	 */
	public void transform(Matrix m) {
   		Iterator i = getPoints().iterator();
    	while (i.hasNext()) {
    		((Point)i.next()).mul(m);
    	}
		
    	// transform children
    	if (getChildren() != null) {
    		Iterator child = getChildren().iterator();
    		while (child.hasNext()) {
    			Model model = (Model)child.next();
    			Point 	mHome = model.getHome();
    			int dX = (int)getHome().getX() - (int)mHome.getX(),
					dY = (int)getHome().getY() - (int)mHome.getY(),
					dZ = (int)getHome().getZ() - (int)mHome.getZ();
    			model.translatePoints(-dX, -dY, -dZ);
    			model.transform(m);
    			model.translatePoints(dX, dY, dZ);
    		}
    	}
	}

    public void 	rotate(int Rx, int Ry, int Rz) {
		transformationMatrix.rotate(Rx, Ry, Rz);
		Matrix r = new Matrix(Matrix.noTransformMatrix);
		r.rotate(Rx, Ry, Rz);
		transform(r);
	}

	/**
	 * Translate this by...
	 * @param Dx the delta along the X axis
	 * @param Dy the delta along the Y axis
	 * @param Dz the delta along the Z axis
	 */
	public void		translate(int Dx, int Dy, int Dz) {
		home.translate(Dx, Dy, Dz);
	}
	
	/**
	 * Scale this by...
	 * @param Dx the factor along the X axis
	 * @param Dy the factor along the Y axis
	 * @param Dz the factor along the Z axis
	 */
	public void 	scale(double Sx, double Sy, double Sz) {
		transformationMatrix.scale(Sx, Sy, Sz);
		Matrix s = new Matrix(Matrix.noTransformMatrix);
		s.scale(Sx, Sy, Sz);
		transform(s);
	}
	
	/**
	 * Project this using the specified position of the observation point 
	 * @param Xo is the distance between the 'eye' and the screen along the X axis
	 * @param Yo is the distance between the 'eye' and the screen along the Y axis
	 * @param Zo is the distance between the 'eye' and the screen along the Z axis
	 */
	public void		project(double Xo, double Yo, double Zo) {
    	Iterator i = getPoints().iterator();
    	while (i.hasNext()) {
    		((Point)i.next()).project(Xo, Yo, Zo);
    	}
	}

	/**
	 * Iso Project this using the specified position of the observation point (on the Z axis) 
	 * @param Zo is the distance between the 'eye' and the screen along the Z axis
	 */
	public void		isoProject(double Zo) {
    	Iterator i = getPoints().iterator();
    	while (i.hasNext()) {
    		((Point)i.next()).isoProject(Zo);
    	}
	}
	
	/**
	 * Compose a model by adding the models' points in models to this
	 * @param models is the vector of models to merge to this
	 * @return the composed model this
	 */
	public Model compose(Vector models) {
		points = null;
		Iterator i = models.iterator();
		while (i.hasNext()) {
			compose((Model)i.next());
		}
		
		return this;
	}

	/**
	 * Compose a model by adding the model' points to this
	 * @param model is the model to merge to this
	 * @return the composed model this
	 */
	public Model compose(Model m) {
		points = null;
		
		// translate the embedded model's actual points according
		// to this' home
		m.changeHome(getHome());
	
		// add polygons
		Iterator i = m.getPolygons().iterator();
		while (i.hasNext()) {
			addPolygon((Point[])i.next());
		}

    	// compose children
    	if (m.getChildren() != null) {
    		Iterator child = m.getChildren().iterator();
    		while (child.hasNext()) {
    			compose((Model)child.next());
    		}
    	}
		return this;
	}
	
	/**
	 * 
	 * @return this' color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 
	 * @param _color is the color to set to this
	 */
	public void setColor(Color _color) {
		color = _color;
	}
	
	public void setName(String _name) {
		name = _name;
	}
	
	public String getName() {
		return name;
	}
	
	public Point	getHome() {
		if (getParent() != null)  {
			Point realHome = new Point(getParent().getHome());
			return (Point)realHome.add(home);
		}
		
		return new Point(home);
	}

	public void		setHome(Point _home) {
		home = new Point(_home);
	}
	
	public Vector	getChildren() {
		return children;
	}
	public Vector	addChild(Model child) {
		if (children == null) {
			children = new Vector();
		}
		
		if (!children.contains(child)) {
			children.add(child);
			child.setParent(this);
		}
		
		return children;
	}
	public void		removeChild(Model child) {
		if (children == null) 
			return;
		
		if (children.contains(child)) {
			children.remove(child);
			child.setParent(null);
		}
		
		if (children != null && children.isEmpty())
			children = null;
	}
	public Model	getParent() {
		return parent;
	}
	public void		setParent(Model _parent) {
		if (parent == _parent)
			return;
		
		if (parent != null)
			parent.removeChild(this);
		
		parent = _parent;
		if (_parent != null) {
			parent.addChild(this);
		}
	}
	
	/* inspection stuff: shall be moved to an observer... */
	private static final int NAME_PROPERTY 		= 0,
						     HOME_X_PROPERTY 	= 1,
							 HOME_Y_PROPERTY 	= 2,
							 HOME_Z_PROPERTY 	= 3,
							 COLOR_PROPERTY 	= 4,
							 NUM_PROPERTIES 	= 5;

	JPanel	doNothingPanel = new JPanel();
	
	String 		[]names = {"name", 
							"home x", 
							"home y", 
							"home z",
							"color"};
	String 		[]tips = {"name of the model", 
							"model's home x position (inside parent)", 
							"model's home y position (inside parent)", 
							"model's home z position (inside parent)", 
							"color of the model"};

	JComponent 	[]renderers = {new JTextField(name),
							   new JSpinner(),
							   new JSpinner(),
							   new JSpinner(),
							   new JColorChooser()};
	
	protected int getNumProperties() {
		return NUM_PROPERTIES;
	}
	
	public String []getPropertyNames() {
		return names;
	}

	public String []getPropertyTips() {
		return tips;
	}

	public JComponent []getPropertyRenderers() {
		return renderers;
	}
	
	public void initProperties() {
		((JTextField)renderers[NAME_PROPERTY]).setText(name);
		((JSpinner)renderers[HOME_X_PROPERTY]).setValue(new Integer((int)home.getX()));
		((JSpinner)renderers[HOME_Y_PROPERTY]).setValue(new Integer((int)home.getY()));
		((JSpinner)renderers[HOME_Z_PROPERTY]).setValue(new Integer((int)home.getZ()));
		
		JColorChooser colorPicker = ((JColorChooser)renderers[COLOR_PROPERTY]);
		if (colorPicker.getPreviewPanel() != doNothingPanel) {
			colorPicker.setPreviewPanel(doNothingPanel);
		}
		colorPicker.setColor(getColor()); 
	}

	public void applyProperties() {
		Point newHome = new Point(0, 0, 0);
		
		name = ((JTextField)renderers[NAME_PROPERTY]).getText();
		Integer i = (Integer)((JSpinner)renderers[HOME_X_PROPERTY]).getValue();
		newHome.setX(i.intValue());
		i = (Integer)((JSpinner)renderers[HOME_Y_PROPERTY]).getValue();
		newHome.setY(i.intValue());
		i = (Integer)((JSpinner)renderers[HOME_Z_PROPERTY]).getValue();
		newHome.setZ(i.intValue());
		changeHome(newHome);
		
		setColor(((JColorChooser)renderers[COLOR_PROPERTY]).getColor());
		setColor(((JColorChooser)renderers[COLOR_PROPERTY]).getColor());
	}

	public void cancelProperties() {
		initProperties();
	}
	
	protected Point []duplicatePoints(Point []array) {
		Point []newArray = new Point[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = new Point(array[i]);
		}
		
		return newArray;
	}
	
	protected void rotatePoints(Point []array, int Rx, int Ry, int Rz) {
		for (int i = 0; i < array.length; i++) {
			array[i].rotate(Rx, Ry, Rz);
		}
		
	}
	
	public void addPolygon(Point []polygon) {
		if (polygon.length <= 2) 
			return;
		
		// add the connections to the model.
		int i = 0; 
		do {
			Connection c = Connection.factory(this, polygon[i]);
			c.connect(polygon[++i]);
		} while (i < polygon.length - 1);
		polygons.add(polygon);
	}

	public void addPolygons(Vector polygons) {
   		Iterator i = polygons.iterator();
    	while (i.hasNext()) {
    		addPolygon((Point [])i.next());
    	}
	}

	public Vector getPolygons() {
		return polygons;
	}
	
	public void 	setTransformation(Matrix matrix) {
		if (getClassId() == new Model().getClassId())
			return;
		
		transformationMatrix = new Matrix(matrix);
		transform(matrix);
	}
}
