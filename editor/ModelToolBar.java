package editor;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;

public class ModelToolBar extends JToolBar {
	private static final Integer  MIN_ROTATE = new Integer(0),
							  	  MAX_ROTATE = new Integer(360),
							  	  ROTATE_INCREMENT = new Integer(1);
	
	private static final Integer  MIN_TRANSLATE= new Integer(-100),
							  	  MAX_TRANSLATE = new Integer(100),
							  	  TRANSLATE_INCREMENT = new Integer(1);

	Editor 			editor;
	EditorActions	editorActions;
	
	JButton			loadButton,
					saveButton,
					rotateXButton,
					rotateYButton,
					rotateZButton,
					translateXButton,
					translateYButton,
					translateZButton;
	Integer			translateValue = new Integer(0),
					rotateValue = new Integer(0);
	JSpinner		translateSpinner,
					rotateSpinner;
	
	public ModelToolBar(Editor _editor) {
		super();
		editor = _editor;
		
		add(getLoadButton());
		add(getSaveButton());
		add(new JSeparator());
		add(getRotateSpinner());
		add(getRotateXButton());
		add(getRotateYButton());
		add(getRotateZButton());
		add(new JSeparator());
		add(getTranslateSpinner());
		add(getTranslateXButton());
		add(getTranslateYButton());
		add(getTranslateZButton());
	}
	
	public EditorActions getEditorActions() {
		if (editorActions == null)
			editorActions = new EditorActions(editor);

		return editorActions;
	}
	
	JButton getLoadButton() {
		if (loadButton == null) {
			loadButton = new JButton("Load"); 
			loadButton.setMnemonic(java.awt.event.KeyEvent.VK_L);
			loadButton.setToolTipText("load a previously saved model");
			loadButton.addActionListener(getEditorActions().new LoadModel());
		}
		
		return loadButton;
	}

	JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton("Save"); 
			saveButton.setMnemonic(java.awt.event.KeyEvent.VK_M);
			saveButton.setToolTipText("save selected model");
			saveButton.addActionListener(getEditorActions().new SaveSelectedModels());
		}
		
		return saveButton;
	}

	JButton getRotateXButton() {
		if (rotateXButton == null) {
			rotateXButton = new JButton("X.Rotate"); 
			rotateXButton.setToolTipText("rotate selected model around its x axis");
			rotateXButton.addActionListener(getEditorActions().new RotateSelectedModels(rotateSpinner, 1, 0, 0));
		}
		
		return rotateXButton;
	}

	JButton getRotateYButton() {
		if (rotateYButton == null) {
			rotateYButton = new JButton("Y.Rotate"); 
			rotateYButton.setToolTipText("rotate selected model around its y axis");
			rotateYButton.addActionListener(getEditorActions().new RotateSelectedModels(rotateSpinner, 0, 1, 0));
		}
		
		return rotateYButton;
	}

	JButton getRotateZButton() {
		if (rotateZButton == null) {
			rotateZButton = new JButton("Z.Rotate"); 
			rotateZButton.setToolTipText("rotate selected model around its z axis");
			rotateZButton.addActionListener(getEditorActions().new RotateSelectedModels(rotateSpinner, 0, 0, 1));
		}
		
		return rotateZButton;
	}

	JButton getTranslateXButton() {
		if (translateXButton == null) {
			translateXButton = new JButton("X.Translate"); 
			translateXButton.setToolTipText("translate selected model on its x axis");
			translateXButton.addActionListener(getEditorActions().new TranslateSelectedModels(translateSpinner, 1, 0, 0));
		}
		
		return translateXButton;
	}

	JButton getTranslateYButton() {
		if (translateYButton == null) {
			translateYButton = new JButton("Y.Translate"); 
			translateYButton.setToolTipText("translate selected model on its y axis");
			translateYButton.addActionListener(getEditorActions().new TranslateSelectedModels(translateSpinner, 0, 1, 0));
		}
		
		return translateYButton;
	}

	JButton getTranslateZButton() {
		if (translateZButton == null) {
			translateZButton = new JButton("Z.Translate"); 
			translateZButton.setToolTipText("translate selected model on its z axis");
			translateZButton.addActionListener(getEditorActions().new TranslateSelectedModels(translateSpinner, 0, 0, 1));
		}
		
		return translateZButton;
	}

	JSpinner getTranslateSpinner() {
		if (translateSpinner == null) {
			translateSpinner = new JSpinner(new SpinnerNumberModel(translateValue, MIN_TRANSLATE, MAX_TRANSLATE, TRANSLATE_INCREMENT)); 
		}
		
		return translateSpinner;
	}

	JSpinner getRotateSpinner() {
		if (rotateSpinner == null) {
			rotateSpinner = new JSpinner(new SpinnerNumberModel(rotateValue, MIN_ROTATE, MAX_ROTATE, ROTATE_INCREMENT)); 
		}
		
		return rotateSpinner;
	}
}
