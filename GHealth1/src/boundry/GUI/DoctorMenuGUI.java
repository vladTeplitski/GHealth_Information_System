package boundry.GUI;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import control.DoctorController;
import boundry.utility.UtilltyGUI;
import entity.Patient;

/**
 * 
 * @author yafit aronovich
 *
 */
public class DoctorMenuGUI extends AbstractMenuGUI {
	/*defining variables */
	
	private static final long serialVersionUID = 4175993677057835350L;
	private JButton manageFile;
	private JButton newAppointment;
	private JButton generateReferral;
	private JButton createTestReferral;

	/**
	 * DoctorMenuGUI constructor that gets patient
	 * setting title
	 * ActionListener of backward button and returning to DoctorMainGUI screen
	 * @param patient
	 */
	public DoctorMenuGUI(Patient patient) {
		setTitle("Doctor Menu - " + patient.getID());
		backward.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for (AbstractGUI abstractGUI : menuWindows) {
					abstractGUI.backward.doClick();
				}
				new DoctorMainGUI();
				dispose();

			}
		});
		
		manageFile = new JButton("Manage file");
		manageFile.setFont(bigText);
		manageFile.setAlignmentX(CENTER_ALIGNMENT);
		container.add(manageFile);
		manageFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractMenuGUI.menuWindows.add(new MedicalFileGUI(patient));
			}
		});
		
		container.add(Box.createRigidArea(new Dimension(20,20)));
		
		/* creating new appointment button and his designs and put it in the center*/
		newAppointment = new JButton("New appointment");
		newAppointment.setFont(bigText);
		newAppointment.setAlignmentX(CENTER_ALIGNMENT);
		container.add(newAppointment);
		newAppointment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int appID = DoctorController.find_appID(patient);
				System.out.println(appID);
				if(appID == -1)
					JOptionPane.showMessageDialog(null, "There isn't an apponiment of such patient today", "Error", JOptionPane.WARNING_MESSAGE, null);
				else
					AbstractMenuGUI.menuWindows.add(new AppointmentGUI(patient, appID, true));

			}
		});

		container.add(Box.createRigidArea(new Dimension(20,20)));
	
		/*  creating generateReferral button and his designs and put it in the center 
		 *returning to the test referral gui screen 
		 */
		generateReferral = new JButton("Referral to a doctor");
		generateReferral.setFont(bigText);
		generateReferral.setAlignmentX(CENTER_ALIGNMENT);
		container.add(generateReferral);
		generateReferral.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractMenuGUI.menuWindows.add(new ReferralGUI(patient, true));
			}
		});
		
		container.add(Box.createRigidArea(new Dimension(20,20)));

		createTestReferral = new JButton("Test referral");
		createTestReferral.setFont(bigText);
		createTestReferral.setAlignmentX(CENTER_ALIGNMENT);
		container.add(createTestReferral);
		createTestReferral.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractMenuGUI.menuWindows.add(new TestreferralGUI(patient));
			}
		});

		setVisible(true);
	}

}