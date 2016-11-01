package boundry.GUI;

import javax.mail.MessagingException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import email.EmailController;
import entity.Patient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import ocsf.Main.MainClient;
import control.OperatorContoller;
import boundry.utility.ReferralIDSearch;

import java.awt.Component;
import java.awt.Font;

/**
 * 
 * @author shay zafran
 *
 */

public class OperatorMenuGUI extends AbstractMenuGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton cancelAppointment;
	private JButton addAppointment;
	private Patient patient;
	private JTextField CheckAppointmentCancel;
	private JLabel refferalid;
	public boolean stopLoop=true;
	private JTextField textField;
	public int apcID;
	//private JTextField specialistDOctor;


	/**
	 * OperatorMenuGUI constructor
	 * @param patient is an appearance of patient that talks with the operator
	 * creating add appointment and cancel appointment buttons
	 */
	public OperatorMenuGUI(Patient patient) {
		setTitle("Operator Menu");
		
		AbstractGUI abstractGUI = this;
		//presses on Add Appointment//
		JButton btnNewButton = new JButton("Add Appointment");
		btnNewButton.setFont(bigText);
		btnNewButton.setAlignmentX(CENTER_ALIGNMENT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			ReferralIDSearch ref=new ReferralIDSearch(patient, abstractGUI);
			}	
		});
		container.add(btnNewButton);
		
		container.add(Box.createRigidArea(new Dimension(20,20)));
		
		//presses on Cancel Appointment//
		JButton btnCancelAppointment = new JButton("Cancel Appointment");
		btnCancelAppointment.setFont(bigText);
		btnCancelAppointment.setAlignmentX(CENTER_ALIGNMENT);
		btnCancelAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  //Open a window for search appointment id.
				String s = (String)JOptionPane.showInputDialog(
				                    getContentPane(),
				                    "Enter the Appointment ID:",    
				                    "Cancel Appointment",
				                    JOptionPane.PLAIN_MESSAGE,
				                   null,
				                    null,
				                    null);
                 
		
		 apcID=Integer.parseInt(s);
         System.out.println("the apcID "+  apcID+"\nemail "+ patient.getEmail());
         OperatorContoller.cancelAppointment(apcID,patient.getEmail(),patient.getID());

			}
		});
		container.add(btnCancelAppointment);
		
		container.add(Box.createRigidArea(new Dimension(20,20)));


		//Presses on back ward button-back to operator Main GUI
        backward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new OperatorMainGUI();			
			}
		});
		
		
		setVisible(true);
		
	}
}