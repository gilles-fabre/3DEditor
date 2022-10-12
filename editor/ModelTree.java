package editor;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import utilities.SimpleStringTokenizer;

import model.Model;

public class ModelTree extends JTree {
	Editor 				   editor;
	DefaultMutableTreeNode root;
	
	public ModelTree(Editor _editor, DefaultMutableTreeNode _root) {
		super(_root);
		root = _root;
		editor = _editor;
		addTreeSelectionListener(new ModelTreeListener());
		root.setAllowsChildren(true);
		setEditable(false);
		getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		setShowsRootHandles(true);
	}
	
	class ModelTreeListener implements TreeSelectionListener {

		public void valueChanged(TreeSelectionEvent arg0) {
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)getLastSelectedPathComponent();
		    if (node == null) 
		    	return;
		    
		    toggleSelection(node);
		}
	}

	private void toggleSelection(DefaultMutableTreeNode node) {
		clearSelection();
		if (node.isRoot()) {
			editor.getSelectedModels().clear();
		}
		else {
			Model m = (Model)node.getUserObject();
			if (!editor.getSelectedModels().contains(m)) {
				editor.getSelectedModels().add(m);
			}
			else { 
				editor.getSelectedModels().remove(m);
			}
		}
		
		editor.getEditorGUI().getMainWindow().updateAll(editor.getEditorGUI().getModelsTree());
	}
	
	private ModelTreeNode getModeTreeNode(DefaultMutableTreeNode root, Model m) {
		Enumeration child = root.children();
		while (child.hasMoreElements()) {
			ModelTreeNode node = (ModelTreeNode)child.nextElement();
			if (node.getUserObject().equals(m))
				return node;
		}
		
		return null;
	}
	
	private void	removeOrphanTreeNodes(DefaultMutableTreeNode root, Vector models) {
		Vector orphans = new Vector();

		Enumeration child = root.children();
		while (child.hasMoreElements()) {
			ModelTreeNode node = (ModelTreeNode)child.nextElement();
			if (!models.contains(node.getUserObject()))
				orphans.add(node);
		}
		
		Iterator i = orphans.iterator();
		while (i.hasNext()) {
			root.remove((ModelTreeNode)i.next());
		}
	}

	private void 	updateSubTree(DefaultMutableTreeNode root, Vector models) {
		if (models == null) {
			root.removeAllChildren();
			return;
		}
		
		Iterator i = models.iterator();
		while (i.hasNext()) {
			Model m = (Model)i.next();
			ModelTreeNode mNode = getModeTreeNode(root, m);
			if (mNode == null) {
				mNode = new ModelTreeNode(m);
				root.add(mNode);
			}
			mNode.update();
		}
		
		removeOrphanTreeNodes(root, models);
	}
	
	public void updateUI() {
		if (editor != null)
			updateSubTree(getRoot(), editor.getModels());
		
		super.updateUI();
	}
	
	public DefaultMutableTreeNode getRoot() {
		return root;
	}
	
	class ModelTreeNode extends DefaultMutableTreeNode {
		public ModelTreeNode(Model _model) {
			userObject = _model;
		}
		
		public String toString() {
			Model 	m = (Model)userObject;
			String 	selectionMark = new String(Editor.editor.getSelectedModels().contains(m) ? "*" : "");
			
			SimpleStringTokenizer className = new SimpleStringTokenizer(userObject.getClass().getName());
			return selectionMark + new String(className.lastToken() + ":" + m.getName());
		}
		
		public void update() {
			// add children if necessary...
			updateSubTree(this, ((Model)getUserObject()).getChildren());
			
			// recursively update the children
			Enumeration child = children();
			while (child.hasMoreElements()) {
				ModelTreeNode node = (ModelTreeNode)child.nextElement();
				updateSubTree(node, ((Model)node.getUserObject()).getChildren());
			}
		}
	}
	
	private ModelTreeNode getModelTreeNodeAux(DefaultMutableTreeNode subRoot, Model m) {
		int num = subRoot.getChildCount();
		for (int i = 0; i < num; i++) {
			ModelTreeNode node = (ModelTreeNode)subRoot.getChildAt(i); 
			if (node.getUserObject().equals(m))
				return node;
		}
		
		return null;
	}

	public 	ModelTreeNode getModelTreeNode(Model m) {
		return getModelTreeNodeAux(getRoot(), m);
	}
}
