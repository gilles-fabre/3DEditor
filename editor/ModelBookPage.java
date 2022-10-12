package editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.SpringLayout;

import utilities.SpringUtilities;

import model.Model;

public class ModelBookPage extends JPanel {
	static	final int	COMPONENT_SPACING = 7;
	
	Model 		model;
	JButton 	applyButton, cancelButton;
	JScrollPane propertiesView;
	JPanel 		propertiesPane;

	public ModelBookPage(Model _model) {
		model = _model;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		layout.putConstraint(SpringLayout.SOUTH, getPropertiesView(), -COMPONENT_SPACING, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, getPropertiesView(), -COMPONENT_SPACING, SpringLayout.WEST, getApplyButton());
		layout.putConstraint(SpringLayout.WEST, getPropertiesView(), COMPONENT_SPACING, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, getPropertiesView(), COMPONENT_SPACING, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, getApplyButton(), -COMPONENT_SPACING, SpringLayout.WEST, getCancelButton());
		layout.putConstraint(SpringLayout.EAST, getCancelButton(), -COMPONENT_SPACING, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, getApplyButton(), -COMPONENT_SPACING, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.SOUTH, getCancelButton(), -COMPONENT_SPACING, SpringLayout.SOUTH, this);
		add(getPropertiesView(), null);
		add(getApplyButton(), null);
		add(getCancelButton(), null);
		model.initProperties();
		doLayout();
	}
	
	public Model getModel() {
		return model;
	}

	public void updateTitle() {
		ModelBook book = (ModelBook)getParent();
		book.setTitleAt(book.indexOfComponent(this), model.getName());
	}
	public void updateUI() {
		if (model != null)
			model.initProperties();

		super.updateUI();
	}

	public class ApplyAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			model.applyProperties();
			Editor.editor.getEditorGUI().getMainWindow().updateAll(Editor.editor.getEditorGUI().getModelsBook());
			updateTitle();
		}
	}

	public class CancelAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			model.cancelProperties();
			Editor.editor.getEditorGUI().getMainWindow().updateAll(Editor.editor.getEditorGUI().getModelsBook());
		}
	}

	/**
	 * This method initializes applyButton
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getApplyButton() {
		if (applyButton == null) {
			applyButton = new JButton();
			applyButton.setToolTipText("Apply the changes to the model");
			applyButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			applyButton.setVerticalAlignment(javax.swing.SwingConstants.TOP);
			applyButton.setText("Apply");
			applyButton.addActionListener(new ApplyAction());
		}
		return applyButton;
	}

	/**
	 * This method initializes cancelButton
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setToolTipText("Cancel the changes done to the properties");
			cancelButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			cancelButton.setVerticalAlignment(javax.swing.SwingConstants.TOP);
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new CancelAction());
		}
		return cancelButton;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPropertiesView() {
		if (propertiesView == null) {
			propertiesView = new JScrollPane(getPropertiesPane());
			propertiesView.setLayout(new ScrollPaneLayout());
			propertiesView.setBackground(java.awt.Color.white);
			propertiesView.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			propertiesView.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			propertiesView.setAutoscrolls(true);
			propertiesView.doLayout();
		}
		return propertiesView;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPropertiesPane() {
		if (propertiesPane == null) {
			propertiesPane = new JPanel(new SpringLayout());

			String			[]labels = model.getPropertyNames();
			String			[]tips = model.getPropertyTips();
			JComponent		[]renderers = model.getPropertyRenderers();
			int numPairs = labels.length;

			//	populate the panel.
			for (int i = 0; i < numPairs; i++) {
				JComponent c = renderers[i];
				c.setMaximumSize(new Dimension((int)c.getMaximumSize().getWidth(), c.getHeight()));
			    JLabel l = new JLabel(labels[i], JLabel.TRAILING);
				l.setToolTipText(tips[i]);
			    propertiesPane.add(l);
			    l.setLabelFor(c);
			    propertiesPane.add(c);
			}

			// lay out the panel.
			SpringUtilities.makeCompactGrid(propertiesPane,
			                                numPairs, 2, //rows, cols
			                                COMPONENT_SPACING, COMPONENT_SPACING,        //initX, initY
			                                COMPONENT_SPACING, COMPONENT_SPACING);       //xPad, yPad
			propertiesPane.doLayout();
		}

		return propertiesPane;
	}
}
