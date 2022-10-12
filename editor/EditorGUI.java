/**
 * EditorGUI provides all the GUI support for the editor
 * 
 * @author gilles fabre
 * @date April 2006
 */
package editor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.CameraViewRenderer;
import engine.FrontViewRenderer;
import engine.LeftViewRenderer;
import engine.TopViewRenderer;

public class EditorGUI {
   	
	Window		mainWindow;  
	JScrollPane cameraViewPane, 
				topViewPane, 
				leftViewPane, 
				frontViewPane;
	JPanel 		viewsPane;
	ModelBook	modelsBook;
	JScrollPane	treePane;
	JSplitPane	hSplitPane, vSplitPane;
	ModelTree	modelsTree;
	JMenuBar 	menuBar;
	ModelToolBar toolbar;
	JMenu 		fileMenu, 
				editMenu,
				modelMenu,
				modelNewMenu;
	JMenuItem 	saveAllMenuItem, 
				saveMenuItem, 
				loadMenuItem,
				cutMenuItem,
				copyMenuItem,
				pasteMenuItem,
				selectAllMenuItem,
				unselectAllMenuItem,
				composeMenuItem,
				deleteMenuItem,
				deleteAllMenuItem,
				modelNewBoxMenuItem,
				modelNewDiskMenuItem,
				modelNewConeMenuItem,
				modelNewPyramidMenuItem,
				modelNewCylinderMenuItem,
				modelNewSphereMenuItem,
				modelNewTetrahedronMenuItem,
				modelNewTorusMenuItem,
				exitMenuItem;
	EditorActions editorActions;
	Editor		editor;
	DefaultMutableTreeNode 	root = new DefaultMutableTreeNode("3D models");

	public class Window extends JFrame {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * default constructor enables events processing.
		 * Resize events will be used to dynamically adjust the Editor's panes layout
		 */
		public Window() {
			enableEvents(ComponentEvent.COMPONENT_RESIZED);
		}
		
		/**
		 * process COMPONENT_RESIZED event to set the split panes positions.
		 */
		protected void processComponentEvent(ComponentEvent e) {
			super.processComponentEvent(e);
			if (e.getID() == ComponentEvent.COMPONENT_RESIZED) {
				if (vSplitPane != null) 
					vSplitPane.setDividerLocation(3 * getHeight() / 4);
				if (hSplitPane != null) 
					hSplitPane.setDividerLocation(getWidth() / 4);
			}
		}
		
		public void updateAll(JComponent source) {
			if (getModelsTree() != null && (source == null || !source.equals(getModelsTree())))
				getModelsTree().updateUI();
			if (getModelsBook() != null && (source == null || !source.equals(getModelsBook())))
				getModelsBook().updateUI();
			super.repaint();
		}
	}
	
	public EditorGUI(Editor _editor) {
		editor = _editor;
	}
	
	public ModelTree getModelsTree() {
		return modelsTree;
	}

	public ModelBook getModelsBook() {
		return modelsBook;
	}

	/**jMenuItem
	 * This method initializes mainWindow	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	public Window getMainWindow() {
		if (mainWindow == null) {
			mainWindow = new Window();
			mainWindow.setTitle("3DEngine");
			mainWindow.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			mainWindow.setLocation(new java.awt.Point(0,0));
			mainWindow.setJMenuBar(getMenuBar());
			mainWindow.setSize(new java.awt.Dimension(800,600));
			
			modelsTree = new ModelTree(editor, root);
			treePane = new JScrollPane(modelsTree);
			modelsBook = new ModelBook(editor);
			
			viewsPane = new JPanel(new GridLayout(2, 2));
			viewsPane.add(getCameraViewPane());
			viewsPane.add(getTopViewPane());
			viewsPane.add(getLeftViewPane());
			viewsPane.add(getFrontViewPane());

			vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, viewsPane, modelsBook);
			vSplitPane.setDividerLocation(3 * mainWindow.getHeight() / 4);

			hSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, vSplitPane);
			hSplitPane.setDividerLocation(mainWindow.getWidth() / 4);
	        mainWindow.add(getToolBar(), BorderLayout.PAGE_START);
			mainWindow.add(hSplitPane, BorderLayout.CENTER);
		}
		return mainWindow;
	}
	
	/**
	 * This method initializes JScrollPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JScrollPane getFrontViewPane() {
		if (frontViewPane == null) {
			frontViewPane = new JScrollPane(new FrontViewRenderer());
			frontViewPane.setLayout(new ScrollPaneLayout());
			frontViewPane.setBackground(java.awt.Color.white);
			frontViewPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			frontViewPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			frontViewPane.setAutoscrolls(false);
			frontViewPane.doLayout();
		}
		return frontViewPane;
	}
	private JScrollPane getLeftViewPane() {
		if (leftViewPane == null) {
			leftViewPane = new JScrollPane(new LeftViewRenderer());
			leftViewPane.setLayout(new ScrollPaneLayout());
			leftViewPane.setBackground(java.awt.Color.white);
			leftViewPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			leftViewPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			leftViewPane.setAutoscrolls(false);
			leftViewPane.doLayout();
		}
		return leftViewPane;
	}
	private JScrollPane getTopViewPane() {
		if (topViewPane == null) {
			topViewPane = new JScrollPane(new TopViewRenderer());
			topViewPane.setLayout(new ScrollPaneLayout());
			topViewPane.setBackground(java.awt.Color.white);
			topViewPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			topViewPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			topViewPane.setAutoscrolls(false);
			topViewPane.doLayout();
		}
		return topViewPane;
	}
	private JScrollPane getCameraViewPane() {
		if (cameraViewPane == null) {
			cameraViewPane = new JScrollPane(new CameraViewRenderer());
			cameraViewPane.setLayout(new ScrollPaneLayout());
			cameraViewPane.setBackground(java.awt.Color.white);
			cameraViewPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			cameraViewPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			cameraViewPane.setAutoscrolls(false);
			cameraViewPane.doLayout();
		}
		return cameraViewPane;
	}
	
	public JPanel getViewPane() {
		return viewsPane;
	}

	
	/**
	 * This method initializes mainBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getFileMenu());
			menuBar.add(getEditMenu());
			menuBar.add(getModelMenu());
		}
		return menuBar;
	}

	/**
	 * This method initializes modelMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getModelMenu() {
		if (modelMenu == null) {
			modelMenu = new JMenu();
			modelMenu.setText("Model");
			modelMenu.add(getComposeMenuItem());
			modelMenu.add(getDeleteMenuItem());
			modelMenu.add(getDeleteAllMenuItem());
			modelMenu.add(getModelNewMenu());
		}
		return modelMenu;
	}

	/**
	 * This method initializes modelNewMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getModelNewMenu() {
		if (modelNewMenu == null) {
			modelNewMenu = new JMenu();
			modelNewMenu.setText("New");
			modelNewMenu.add(getModelNewBoxMenuItem());
			modelNewMenu.add(getModelNewDiskMenuItem());
			modelNewMenu.add(getModelNewSphereMenuItem());
			modelNewMenu.add(getModelNewCylinderMenuItem());
			modelNewMenu.add(getModelNewConeMenuItem());
			modelNewMenu.add(getModelNewPyramidMenuItem());
			modelNewMenu.add(getModelNewTetrahedronMenuItem());
			modelNewMenu.add(getModelNewTorusMenuItem());
		}
		return modelNewMenu;
	}
	
	/**
	 * This method initializes modelNewTetrahedronMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getModelNewTetrahedronMenuItem() {
		if (modelNewTetrahedronMenuItem == null) {
			modelNewTetrahedronMenuItem = new JMenuItem();
			modelNewTetrahedronMenuItem.setText("Tetrahedron");
			modelNewTetrahedronMenuItem.setToolTipText("add a Tetrahedron");
			modelNewTetrahedronMenuItem.addActionListener(getEditorActions().new AddTetrahedron());
		}
		return modelNewTetrahedronMenuItem;
	}

	/**
	 * This method initializes modelNewPyramidMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getModelNewPyramidMenuItem() {
		if (modelNewPyramidMenuItem == null) {
			modelNewPyramidMenuItem = new JMenuItem();
			modelNewPyramidMenuItem.setText("Pyramid");
			modelNewPyramidMenuItem.setToolTipText("add a Pyramid");
			modelNewPyramidMenuItem.addActionListener(getEditorActions().new AddPyramid());
		}
		return modelNewPyramidMenuItem;
	}

	/**
	 * This method initializes modelNewBoxMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getModelNewBoxMenuItem() {
		if (modelNewBoxMenuItem == null) {
			modelNewBoxMenuItem = new JMenuItem();
			modelNewBoxMenuItem.setText("Box");
			modelNewBoxMenuItem.setToolTipText("add a Box");
			modelNewBoxMenuItem.addActionListener(getEditorActions().new AddBox());
		}
		return modelNewBoxMenuItem;
	}

	/**
	 * This method initializes modelNewConeMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getModelNewConeMenuItem() {
		if (modelNewConeMenuItem == null) {
			modelNewConeMenuItem = new JMenuItem();
			modelNewConeMenuItem.setText("Cone");
			modelNewConeMenuItem.setToolTipText("add a Cone");
			modelNewConeMenuItem.addActionListener(getEditorActions().new AddCone());
		}
		return modelNewConeMenuItem;
	}

	/**
	 * This method initializes modelNewSphereMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getModelNewSphereMenuItem() {
		if (modelNewSphereMenuItem == null) {
			modelNewSphereMenuItem = new JMenuItem();
			modelNewSphereMenuItem.setText("Sphere");
			modelNewSphereMenuItem.setToolTipText("add a Sphere");
			modelNewSphereMenuItem.addActionListener(getEditorActions().new AddSphere());
		}
		return modelNewSphereMenuItem;
	}

	/**
	 * This method initializes modelNewCylinderMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getModelNewCylinderMenuItem() {
		if (modelNewCylinderMenuItem == null) {
			modelNewCylinderMenuItem = new JMenuItem();
			modelNewCylinderMenuItem.setText("Cylinder");
			modelNewCylinderMenuItem.setToolTipText("add a Cylinder");
			modelNewCylinderMenuItem.addActionListener(getEditorActions().new AddCylinder());
		}
		return modelNewCylinderMenuItem;
	}

	/**
	 * This method initializes modelNewDiskMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getModelNewDiskMenuItem() {
		if (modelNewDiskMenuItem == null) {
			modelNewDiskMenuItem = new JMenuItem();
			modelNewDiskMenuItem.setText("Disk");
			modelNewDiskMenuItem.setToolTipText("add a Disk");
			modelNewDiskMenuItem.addActionListener(getEditorActions().new AddDisk());
		}
		return modelNewDiskMenuItem;
	}

	/**
	 * This method initializes modelNewTorusMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getModelNewTorusMenuItem() {
		if (modelNewTorusMenuItem == null) {
			modelNewTorusMenuItem  = new JMenuItem();
			modelNewTorusMenuItem .setText("Torus");
			modelNewTorusMenuItem .setToolTipText("add a Torus");
			modelNewTorusMenuItem .addActionListener(getEditorActions().new AddTorus());
		}
		return modelNewTorusMenuItem ;
	}

	public EditorActions getEditorActions() {
		if (editorActions == null)
			editorActions = new EditorActions(editor);

		return editorActions;
	}
	
	/**
	 * This method initializes deleteAllMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDeleteAllMenuItem() {
		if (deleteAllMenuItem == null) {
			deleteAllMenuItem = new JMenuItem();
			deleteAllMenuItem.setText("Delete all");
			deleteAllMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_X);
			deleteAllMenuItem.setToolTipText("delete all models");
			deleteAllMenuItem.addActionListener(getEditorActions().new DeleteAllModels());
		}
		return deleteAllMenuItem;
	}

	/**
	 * This method initializes deleteMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDeleteMenuItem() {
		if (deleteMenuItem == null) {
			deleteMenuItem = new JMenuItem();
			deleteMenuItem.setText("Delete");
			deleteMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_D);
			deleteMenuItem.setToolTipText("delete selected models");
			deleteMenuItem.addActionListener(getEditorActions().new DeleteSelectedModels());
		}
		return deleteMenuItem;
	}

	/**
	 * This method initializes composeMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getComposeMenuItem() {
		if (composeMenuItem == null) {
			composeMenuItem = new JMenuItem();
			composeMenuItem.setText("Compose");
			composeMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_O);
			composeMenuItem.setToolTipText("compose selected models");
			composeMenuItem.addActionListener(getEditorActions().new ComposeSelectedModels());
		}
		return composeMenuItem;
	}

	/**
	 * This method initializes saveMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_M);
			saveMenuItem.setToolTipText("save selected model");
			saveMenuItem.addActionListener(getEditorActions().new SaveSelectedModels());
		}
		return saveMenuItem;
	}

	/**
	 * This method initializes saveAllMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveAllMenuItem() {
		if (saveAllMenuItem == null) {
			saveAllMenuItem = new JMenuItem();
			saveAllMenuItem.setText("Save All");
			saveAllMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_S);
			saveAllMenuItem.setToolTipText("save all models");
			saveAllMenuItem.addActionListener(getEditorActions().new SaveAllModels());
		}
		return saveAllMenuItem;
	}

	/**
	 * This method initializes loadMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getLoadMenuItem() {
		if (loadMenuItem == null) {
			loadMenuItem = new JMenuItem();
			loadMenuItem.setText("Load...");
			loadMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_L);
			loadMenuItem.setToolTipText("load a previously saved model");
			loadMenuItem.addActionListener(getEditorActions().new LoadModel());
		}
		return loadMenuItem;
	}

	/**
	 * This method initializes copyMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCopyMenuItem() {
		if (copyMenuItem == null) {
			copyMenuItem = new JMenuItem();
			copyMenuItem.setText("Copy");
			copyMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_C);
			copyMenuItem.setToolTipText("copy selected model");
			copyMenuItem.addActionListener(getEditorActions().new CopySelectedModels());
		}
		return copyMenuItem;
	}

	/**
	 * This method initializes cutMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCutMenuItem() {
		if (cutMenuItem == null) {
			cutMenuItem = new JMenuItem();
			cutMenuItem.setText("Cut");
			cutMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_U);
			cutMenuItem.setToolTipText("cut selected model");
			cutMenuItem.addActionListener(getEditorActions().new CutSelectedModels());
		}
		return cutMenuItem;
	}

	/**
	 * This method initializes cutMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getPasteMenuItem() {
		if (pasteMenuItem == null) {
			pasteMenuItem = new JMenuItem();
			pasteMenuItem.setText("Paste");
			pasteMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_P);
			pasteMenuItem.setToolTipText("paste cut/copied model");
			pasteMenuItem.addActionListener(getEditorActions().new PasteModels());
		}
		return pasteMenuItem;
	}

	/**
	 * This method initializes selectAllMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSelectAllMenuItem() {
		if (selectAllMenuItem == null) {
			selectAllMenuItem = new JMenuItem();
			selectAllMenuItem.setText("Select All");
			selectAllMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_E);
			selectAllMenuItem.setToolTipText("select all models");
			selectAllMenuItem.addActionListener(getEditorActions().new SelectAllModels());
		}
		return selectAllMenuItem;
	}

	/**
	 * This method initializes UnselectAllMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getUnselectAllMenuItem() {
		if (unselectAllMenuItem == null) {
			unselectAllMenuItem = new JMenuItem();
			unselectAllMenuItem.setText("Unselect All");
			unselectAllMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_A);
			unselectAllMenuItem.setToolTipText("unselect all models");
			unselectAllMenuItem.addActionListener(getEditorActions().new UnselectAllModels());
		}
		return unselectAllMenuItem;
	}

	/**
	 * This method initializes exitMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.setToolTipText("exit the application");
			exitMenuItem.addActionListener(getEditorActions().new ExitAction());
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes editMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText("Edit");
			editMenu.add(getSelectAllMenuItem());
			editMenu.add(getUnselectAllMenuItem());
			editMenu.add(new JSeparator());
			editMenu.add(getCutMenuItem());
			editMenu.add(getCopyMenuItem());
			editMenu.add(getPasteMenuItem());
		}
		return editMenu;
	}

	/**
	 * This method initializes fileMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getSaveAllMenuItem());
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getLoadMenuItem());
			fileMenu.add(new JSeparator());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}
	
	/** 
	 * This methid initializes toolbar
	 * 
	 * @return ModelToolBar
	 */
	private ModelToolBar getToolBar() {
		if (toolbar == null) {
			toolbar = new ModelToolBar(editor);
		}
		return toolbar;
	}
}
