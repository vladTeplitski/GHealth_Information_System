package boundry.GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import control.DoctorController;
import boundry.utility.MyJLabel;
import boundry.utility.MyJTextfield;
import entity.Patient;

/**
 * 
 * @author yafit aronovich
 *
 */
public class ReferralGUI extends AbstractReferralGUI {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> combo;

	/**
	 * ReferralGUI constructor
	 * @param patient is appearance of patient that for him the doctor is making the referral
	 * @param flag to edit and the other is for selecting in the combobox
	 * we have here kind of doctor and description 
	 * backward button that returns to  DoctorMenuGUI screen
	 * forward button that returns to  DoctorMenuGUI screen
	 */
	
	public ReferralGUI(Patient patient,boolean flag) {
		
		super(patient);
		setTitle("referral screen");
		
		combo = new JComboBox<String>();
		combo.setBounds(frameWidth/2, frameHeight/2-200, 150, 30);
		combo.setFont(smallText);
		getContentPane().add(combo);
		for (String string : doctors) {
			combo.addItem(string);
		}

		desc.setEditable(flag);
		combo.setSelectedIndex(0);
		combo.setEnabled(flag);
		
		combo_label.setText("Speciality:");
		Description_label.setText("Description:");

		/*Presses backward button without saving */
		backward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				performMenuClose();
			}});
		
		/*press the next button with saving data*/
		
		forward.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DoctorController.generatingReferral(patient, desc.getText(), (String) combo.getSelectedItem());
				performMenuClose();
			}
		});

		setVisible(true);

	}

}