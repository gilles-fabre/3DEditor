/**
 * EditorActions provides all the ActionListeners for the editor
 * 
 * @author gilles fabre
 * @date April 2006
 */
package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JSpinner;

import model.Model;

public class EditorActions {
	Editor editor;
	
	public EditorActions(Editor _editor) {
		editor = _editor;
	}
	
	public class ExitAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}

	public class AddTorus implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.addTorus();
		}
	}

	public class AddPyramid implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.addPyramid();
		}
	}

	public class AddTetrahedron implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.addTetrahedron();
		}
	}

	public class AddBox implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.addBox();
		}
	}

	public class AddCone implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.addCone();
		}
	}

	public class AddCylinder implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.addCylinder();
		}
	}

	public class AddDisk implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.addDisk();
		}
	}

	public class AddSphere implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.addSphere();
		}
	}

	public class DeleteSelectedModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.deleteSelectedModels();
		}
	}

	public class DeleteAllModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.deleteAllModels();
		}
	}

	public class ComposeSelectedModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.composeSelectedModels();
		}
	}

	public class SelectAllModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.selectAllModels();
		}
	}

	public class UnselectAllModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.unselectAllModels();
		}
	}

	public class CopySelectedModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.copySelectedModels();
		}
	}

	public class CutSelectedModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.cutSelectedModels();
		}
	}

	public class PasteModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.pasteModels();
		}
	}

	public class SaveAllModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.saveAllModels();
		}
	}

	public class SaveSelectedModels implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			editor.saveSelectedModels();
		}
	}
	
	public class TranslateSelectedModels implements ActionListener {

		int 		x, y, z;
		JSpinner 	spinner;
		
		public TranslateSelectedModels(JSpinner _spinner, int _x, int _y, int _z) {
			x = _x;
			y = _y;
			z = _z;
			spinner = _spinner;
		}
		public void actionPerformed(ActionEvent arg0) {
			int v = ((Integer)spinner.getValue()).intValue();

			Iterator i = editor.getSelectedModels().iterator();
			while (i.hasNext()) {
				((Model)i.next()).translate(x * v, y * v, z * v);
			}

			editor.getEditorGUI().getMainWindow().updateAll(null);
		}
	}
 
	public class RotateSelectedModels implements ActionListener {

		int 		x, y, z;
		JSpinner 	spinner;
		
		public RotateSelectedModels(JSpinner _spinner, int _x, int _y, int _z) {
			x = _x;
			y = _y;
			z = _z;
			spinner = _spinner;
		}
		public void actionPerformed(ActionEvent arg0) {
			int v = ((Integer)spinner.getValue()).intValue();

			Iterator i = editor.getSelectedModels().iterator();
			while (i.hasNext()) {
				((Model)i.next()).rotate(x * v, y * v, z * v);
			}

			editor.getEditorGUI().getMainWindow().updateAll(null);
		}
	}

	public class LoadModel implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new Editor.ModelFilter());
		    if(chooser.showOpenDialog(editor.getEditorGUI().getMainWindow()) == JFileChooser.APPROVE_OPTION) 
		    {
		    	File file = chooser.getSelectedFile();
		    	if (!file.canRead() || !file.exists())
		    	{
		    		// display an alert
		    		Alert alert = new Alert(editor.getEditorGUI().getMainWindow(), "couldn't load model", Alert.ERROR_ALERT);
		    		alert.setVisible(true);
					return;
		    	}
		    	
		    	editor.loadModel(file.getPath());
		    }
		}
	}
}
