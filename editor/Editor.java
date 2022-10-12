/**
 * Editor is a simple 3D model editor
 * 
 * @author gilles fabre
 * @date April 2006
 */
package editor;

import java.awt.Color;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.filechooser.FileFilter;

import model.Torus;
import model.Box;
import model.Cone;
import model.Cylinder;
import model.Disk;
import model.Model;
import model.ModelFileHandler;
import model.Pyramid;
import model.Sphere;
import model.Tetrahedron;

public final class Editor {

    // the unique instance of editor
    static	public Editor 	editor = new Editor();
    EditorGUI				editorGUI;
	Vector 					models = new Vector(),
							selectedModels = new Vector(),
							clipboard;
	boolean					cut;

	/**
	 * Recursive method to add a model hierarchy to the models Vector and the 
	 * models tree. The model itself is added (no copy made).
	 * 
	 * @param m is the root of the models subtree to add
	 */
	private void add(Model m) {
		if (getSelectedModels().isEmpty()) {
			// add to root
			getModels().add(m);
		}
		else {
			// add a copy of the model in each selected node as a child...
			Iterator i = getSelectedModels().iterator();
			while (i.hasNext()) {
				Model p = (Model)i.next();
//				p.addChild(m.factory());
				p.addChild(m); 
			}
		}
		getEditorGUI().getMainWindow().updateAll(null);
	}

	private void remove(Model m) {
		if (getModels().contains(m))
			getModels().remove(m);
		else
			m.setParent(null);
			
		getEditorGUI().getMainWindow().updateAll(null);
	}

	public EditorGUI getEditorGUI() {
		if (editorGUI == null)
			editorGUI = new EditorGUI(this);

		return editorGUI;
	}

	public Vector getSelectedModels() {
		return selectedModels;
	}

	public Vector getModels() {
		return models;
	}
	
	public void addTetrahedron() {
        Model m = new Tetrahedron();
        m.setColor(Color.PINK);
		add(m);
		
	}
	
	public void addTorus() {
		Model m = new Torus();
		m.setColor(Color.YELLOW);
        add(m);
	}

	public void addPyramid() {
		Model m = new Pyramid();
		m.setColor(Color.ORANGE);
        add(m);
	}

	public void addBox() {
		Model m = new Box();
		m.setColor(Color.GREEN);
        add(m);
	}

	public void addCone() {
        Model m = new Cone();
        m.setColor(Color.LIGHT_GRAY);
        add(m);
	}

	public void addDisk() {
        Model m = new Disk();
		m.setColor(Color.BLUE);
        add(m);
	}

	public void addCylinder() {
        Model m = new Cylinder();
        m.setColor(Color.MAGENTA);
        add(m);
	}

	public void addSphere() {
        Model m = new Sphere();
        m.setColor(Color.CYAN);
		add(m);
	}

	void run() {
		getEditorGUI().getMainWindow().setVisible(true);
	}
	
	static public class ModelFilter extends FileFilter
	{
		// whether the given file is accepted by this filter.
		public boolean accept(File f)
		{
			 return f.getName().endsWith(".model") || 
			 		f.isDirectory();
		}

		// the description of this filter. For example: "JPG and GIF Images"
		public String getDescription()
		{
			return new String("3DEngine model file"); 
		}
	}

	void loadModel(String filename) {
		ModelFileHandler handler = new ModelFileHandler(filename);
		handler.parse();
		getEditorGUI().getMainWindow().updateAll(null);
	}

	void saveAllModels() {
		Iterator i = getModels().iterator();
		while (i.hasNext()) {
			Model m = (Model)i.next();
			if (m.getName() != null) {
				ModelFileHandler handler = new ModelFileHandler();
				handler.generate(m);
			}
		}
	}

	void composeSelectedModels() {
		Iterator i = getSelectedModels().iterator();
		Model m1 = null;
		while (i.hasNext()) {
			Model m2 = (Model)i.next();
			if (m1 == null)
				m1 = new Model(m2);
			else {
				m1.compose(new Model(m2));
			}

			remove(m2);
		}

		unselectAllModels();
		
		if (m1 != null) {
			add(m1);
		}
	}

	void deleteAllModels() {
		Iterator i = getModels().iterator();
		while (i.hasNext()) {
			Model m = (Model)i.next();
			remove(m);
			i = getModels().iterator();
		}
		
		unselectAllModels();
	}

	void deleteSelectedModels() {
		Iterator i = getSelectedModels().iterator();
		while (i.hasNext()) {
			remove((Model)i.next());
		}
		
		unselectAllModels();
	}

	void saveSelectedModels() {
		Iterator i = getSelectedModels().iterator();
		while (i.hasNext()) {
			Model m = (Model)i.next();
			if (m.getName() != null) {
				ModelFileHandler handler = new ModelFileHandler();
				handler.generate(m);
			}
		}
	}
	
	void selectAllModelsAux(Model m) {
		getSelectedModels().add(m);
		if (m.getChildren() != null) {
			Iterator j = m.getChildren().iterator();
			while (j.hasNext()) {
				selectAllModelsAux((Model)j.next());
			}
		}
	}
	
	void selectAllModels() {
		getSelectedModels().clear();
		Iterator i = getModels().iterator();
		while (i.hasNext()) {
			selectAllModelsAux((Model)i.next());
		}

		getEditorGUI().getMainWindow().updateAll(null);
	}

	void unselectAllModels() {
		getSelectedModels().clear();
		getEditorGUI().getMainWindow().updateAll(null);
	}

	void cutSelectedModels() {
		clipboard = new Vector();
		clipboard.addAll(getSelectedModels());
		unselectAllModels();
		cut = true;
	}

	void copySelectedModels() {
		clipboard = new Vector();
		clipboard.addAll(getSelectedModels());
		unselectAllModels();
		cut = false;
	}

	void pasteModels() {
		if (clipboard == null)
			return;
		
		Iterator i = clipboard.iterator();
		while (i.hasNext()) {
			Model m = (Model)i.next();
			if (cut) {
				getSelectedModels().remove(m);
				remove(m);
				i.remove();
			}
			add(m.factory());
		}

		getEditorGUI().getMainWindow().updateAll(null);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	       //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
            	editor.run();
            }
        });
	}
}
