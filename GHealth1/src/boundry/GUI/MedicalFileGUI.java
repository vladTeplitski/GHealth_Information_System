package boundry.GUI;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import boundry.utility.MyJPanel;
import boundry.utility.MyJTable;
import entity.Patient;
import entity.SpecialistDoctor;
/**
 * 
 * @author asaf regev
 *
 */
public class MedicalFileGUI extends AbstractGUI {
	private static final long serialVersionUID = 1L;

	// defining variables
	
	public static ArrayList<AbstractGUI> medicalFileWindows;
	private JButton TransferMedicalFile;
	private MyJTable appointments;
	private MyJTable test_results;
	private static final String[] gtexts = {"patient ID", "name", "family name", "email", "height"};
	private MyJPanel gPanel;
	private boolean flag;

	/**
	 * constructor MedicalFileGUI
	 * @param patient-the medical file is on him
	 * defining button  Transfer entire medical file
	 * taking details from database
	 */
	public MedicalFileGUI(Patient patient) {
		setTitle("Medical File - " + patient.getID());
		medicalFileWindows = new ArrayList<AbstractGUI>();
		SpecialistDoctor doc = (SpecialistDoctor) LoginGUI.user;
		flag = true;
		
		//Presses backward button//
		backward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for (AbstractGUI abstractGUI : medicalFileWindows) {
					abstractGUI.backward.doClick();
				}
				performMenuClose();
			}});
		
		gPanel = new MyJPanel(gtexts, 20, 10, frameWidth-20, 100);
		getContentPane().add(gPanel);
		setFields(patient);

		String msgAppSpecific = "SELECT appID, appointment.date, appointment.hour, description FROM "
				+ "appointment JOIN operator_appointment_creation ON appID=apcID "
				+ "JOIN specialist_doctor ON specDocID=docID "
				+ "WHERE patientID = " + patient.getID()+" AND Specialization='"+doc.getSpecialization()+"'";
		appointments = MyJTable.newTable(msgAppSpecific, 20, 120, frameWidth/2-50, 480);
		getContentPane().add(appointments.getJps());
		appointments.getColumnModel().getColumn(3).setPreferredWidth(300);
		appointments.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				try{
					JTable table =(JTable) me.getSource();
					Point p = me.getPoint();
					int row = table.rowAtPoint(p);
					if (me.getClickCount() == 2){
						int appID = (int) table.getModel().getValueAt(row, 0);
						medicalFileWindows.add(new AppointmentGUI(patient, appID, false));
					}
				}catch(ArrayIndexOutOfBoundsException e){}
			}
		});

		String msgTestSpecific = "SELECT testID, test_result_description FROM test_result JOIN test_referral ON testResultReferralID=testReferralNum "
				+ "JOIN abstract_referral ON ReferralID=testReferralNum "
				+ "JOIN specialist_doctor ON specDocID=doctorID "
				+ "WHERE refPatientID = " + patient.getID()+" AND Specialization='"+doc.getSpecialization()+"'";
		test_results = MyJTable.newTable(msgTestSpecific, frameWidth/2+120, 120, 500, 370);
		getContentPane().add(test_results.getJps());
		test_results.getColumnModel().getColumn(1).setPreferredWidth(300);
		test_results.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				try{
					JTable table =(JTable) me.getSource();
					Point p = me.getPoint();
					int row = table.rowAtPoint(p);
					if (me.getClickCount() == 2){
						int testID = (int) table.getModel().getValueAt(row, 0);
//						medicalFileWindows.add(new insertLabTestResultsGUI());

					}
				}catch(ArrayIndexOutOfBoundsException e){}
			}
		});
		
		TransferMedicalFile = new JButton("Transfer entire medical file");
		TransferMedicalFile.setBounds(frameWidth/2+120, frameHeight-180, 500, 70);
		TransferMedicalFile.setFont(bigText);
		TransferMedicalFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String msgAppEntire = "SELECT appID, appointment.date, appointment.hour, description FROM "
						+ "appointment JOIN operator_appointment_creation ON appID=apcID "
						+ "WHERE patientID = " + patient.getID();
				String msgTestEntire = "SELECT testID, test_result_description FROM test_result JOIN test_referral ON testResultReferralID=testReferralNum "
						+ "JOIN abstract_referral ON ReferralID=testReferralNum "
						+ "WHERE refPatientID = " + patient.getID();
				if(flag){
					appointments.rePopulate(msgAppEntire);
					test_results.rePopulate(msgTestEntire);
					flag = false;
					TransferMedicalFile.setText("Transfer specific medical file");
				}
				else{
					appointments.rePopulate(msgAppSpecific);
					test_results.rePopulate(msgTestSpecific);
					flag = true;
					TransferMedicalFile.setText("Transfer entire medical file");
				}
					
					
			}});
		getContentPane().add(TransferMedicalFile);

		forward.setVisible(false);
		setVisible(true);
	}

/**
 * setFields method is for taking details of the patient
 * @param patient -medical file is on him so details are about him 
 */
	private void setFields(Patient patient) {
		ArrayList<JTextField> gOrder = gPanel.getgOrder();
		gOrder.get(0).setText(String.valueOf(patient.getID()));
		gOrder.get(0).setEditable(false);
		gOrder.get(1).setText(patient.getName());
		gOrder.get(1).setEditable(false);
		gOrder.get(2).setText(patient.getFamilyName());
		gOrder.get(2).setEditable(false);
		gOrder.get(3).setText(patient.getEmail());
		gOrder.get(3).setEditable(false);
		gOrder.get(4).setText(String.valueOf(patient.getHeight()));
		gOrder.get(4).setEditable(false);
	}

	public void showTableOfMedicalExamination() {
		// TODO - implement MedicalFileGUI.showTableOfMedicalExamination
		throw new UnsupportedOperationException();
	}

	public void showTableOfHistory() {
		// TODO - implement MedicalFileGUI.showTableOfHistory
		throw new UnsupportedOperationException();
	}


}