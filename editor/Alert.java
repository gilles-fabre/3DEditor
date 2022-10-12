package editor;

import java.awt.Frame;

import javax.swing.JDialog;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;

/*
 * Created on Feb 17, 2005
 *
 * This module provides Wow with alert dialog services.
 */

/**
 * @author gilles.fabre
 *
 * @class Alert extends JDialog to display a simple message in a modal dialog
 * with an icon/title depending on the alert type.
 */
public final class Alert extends JDialog 
{
	public static final int ERROR_ALERT = 0,
							WARNING_ALERT = 1,
							INFO_ALERT = 2,
							CONFIRMATION_ALERT = 3;
	private static final String title[] = {"Error", 
										   "Warning",
										   "Information",
										   "Confirmation"};
	
	private	int type = ERROR_ALERT;
	private String message = "Error";
	
	private boolean wasValidated = true;
	JDialog dialog; 
	
	private JButton OkButton = null;
	private JButton CancelButton = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JScrollPane jScrollPane = null;
	private JTextPane MessageTextPane = null;
	
	class OkAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			wasValidated = true;
			dialog.dispose();
		}
	}

	class CancelAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dialog.dispose();
		}
	}

	public Alert(Frame owner) 
	{
		super(owner);
		initialize();
	}	
	
	public Alert(Frame owner, int _type) 
	{
		super(owner);
		type = _type;
		initialize();
	}	
	
	public Alert(Frame owner, String _message) 
	{
		super(owner);
		message = _message;
		initialize();
	}	

	public Alert(Frame owner, String _message, int _type) 
	{
		super(owner);
		type = _type;
		message = _message;
		initialize();
	}	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
        this.setContentPane(getJPanel());
        this.setModal(true);
        this.setSize(346, 226);
        getMessageTextPane().setText(message);
        
        if (type != CONFIRMATION_ALERT)
        	getCancelButton().setVisible(false);
        else
        	wasValidated = false;
        
        setTitle(title[type]);
        dialog = this;
        getOkButton().addActionListener(new OkAction());
        getCancelButton().addActionListener(new CancelAction());
	}
	
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getOkButton() {
		if (OkButton == null) {
			OkButton = new JButton();
			OkButton.setName("OkButton");
			OkButton.setText("OK");
		}
		return OkButton;
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getCancelButton() {
		if (CancelButton == null) {
			CancelButton = new JButton();
			CancelButton.setText("CancelButton");
			CancelButton.setName("Cancel");
		}
		return CancelButton;
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
			jPanel.add(getJPanel1(), java.awt.BorderLayout.SOUTH);
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.add(getOkButton(), null);
			jPanel1.add(getCancelButton(), null);
		}
		return jPanel1;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jScrollPane.setViewportView(getMessageTextPane());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */    
	private JTextPane getMessageTextPane() {
		if (MessageTextPane == null) {
			MessageTextPane = new JTextPane();
			MessageTextPane.setText(""); 
			MessageTextPane.setEditable(false);
			MessageTextPane.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14)); 
		}
		return MessageTextPane;
	}

	/**
	 * This method returns (the user tapped the 'OK' button).	
	 * 	
	 * @return boolean
	 */    
	public boolean wasValidated() {return wasValidated;}

}  //  @jve:decl-index=0:visual-constraint="108,118"
