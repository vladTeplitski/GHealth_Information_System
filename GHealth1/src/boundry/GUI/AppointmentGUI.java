package boundry.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.DoctorController;
import boundry.utility.MyJLabel;
import entity.Patient;
import entity.Appointment;

/**
 * 
 * @author yafit aronovich
 *
 */
public class AppointmentGUI extends AbstractGUI{

	/* defining variables */
	
	private static final long serialVersionUID = 3282355125263831663L;
	private JTextField dateText;
	private JTextField Hour;
	private JTextField BloodPressure;
	private JTextField weight;
	private JTextArea Description;
	private JTextField Diagnosis;

	protected JLabel DateLbl;
	protected JLabel HourLbl;
	protected JLabel BloodPressureLbl;
	protected JLabel HeightLbl;
	protected JLabel DescriptionLbl;
	protected JLabel DiagnosisLbl;
	protected JLabel DoctorLbl;



	
	/** constructor of AppointmentGUI that gets patient
	 * @param patient -the appointment is of the patient
	 * @param appID - show the appointment if appID is not -1
	 * defining the appointment screen :fields,buttons,title    */
	               
	
	public AppointmentGUI(Patient patient, int appID, boolean flag) {
		setTitle("Appointment - " + patient.getName());
		
		setGUI_elements(flag);

		/*Presses backward button and his actionlistener*/
		
		backward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(flag)
					performMenuClose();
				else
					performMedicalFileClose();
			}});
		
		/*Presses forward button and his actionlistener*/
		
		forward.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(flag == false){
						performMenuClose();
					}
					else{
						Appointment appointment = new Appointment(appID, dateText.getText(), Hour.getText(),weight.getText(),
								BloodPressure.getText(), Description.getText(), Diagnosis.getText());
						DoctorController.NewAppointment(patient, appointment);
						performMenuClose();
					}
				} catch (InstantiationException | IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}});
		
		if(flag == false){
			Appointment appointment = new Appointment(appID);
			dateText.setText(appointment.getDate());
			Hour.setText(appointment.getHour());
			BloodPressure.setText(appointment.getBloodPressue());
			weight.setText(Double.toString(appointment.getWeight()));
			Description.setText(appointment.getDescription());
			Diagnosis.setText(appointment.getDiagnosis());
			forward.setVisible(false);
		}

		setVisible(true);

	}


/**
 * this method creates some labels of the appointments and their fields
 * @param flag for edit
 */

	private void setGUI_elements(boolean flag) {
		DateLbl = new MyJLabel();
		DateLbl.setBounds(frameWidth/2-250, frameHeight/2-200, 150, 30);
		DateLbl.setText("Appointment Date");
		DateLbl.setFont(smallText);
		getContentPane().add(DateLbl);

		HourLbl = new MyJLabel();
		HourLbl.setBounds(frameWidth/2-250, frameHeight/2-150, 150, 30);
		HourLbl.setText("Appointment Hour");
		HourLbl.setFont(smallText);
		getContentPane().add(HourLbl);

		BloodPressureLbl = new MyJLabel();
		BloodPressureLbl.setBounds(frameWidth/2-250, frameHeight/2-100, 150, 30);
		BloodPressureLbl.setText("BloodPressure");
		BloodPressureLbl.setFont(smallText);
		getContentPane().add(BloodPressureLbl);

		HeightLbl = new MyJLabel();
		HeightLbl.setBounds(frameWidth/2-250, frameHeight/2-50, 150, 30);
		HeightLbl.setText("Weight");
		HeightLbl.setFont(smallText);
		getContentPane().add(HeightLbl);

		DescriptionLbl = new MyJLabel();
		DescriptionLbl.setBounds(frameWidth/2-250, frameHeight/2,150, 30);
		DescriptionLbl.setText("Description");
		DescriptionLbl.setFont(smallText);
		getContentPane().add(DescriptionLbl);

		DiagnosisLbl = new MyJLabel();
		DiagnosisLbl.setBounds(frameWidth/2-250, frameHeight/2+114, 150, 30);
		DiagnosisLbl.setText("Diagnosis");
		DiagnosisLbl.setFont(smallText);
		getContentPane().add(DiagnosisLbl);


		Date date = new Date();

		dateText = new JTextField(10);
		dateText.setText(displayDate.format(date));
		dateText.setEditable(false);
		dateText.setBounds(frameWidth/2-70, frameHeight/2-200, 161, 36);
		dateText.setFont(smallText);
		getContentPane().add(dateText);

		Hour = new JTextField(10);
		Hour.setText(displayHour.format(date));
		Hour.setEditable(false);
		Hour.setBounds(frameWidth/2-70, frameHeight/2-150, 161, 36);
		Hour.setFont(smallText);
		getContentPane().add(Hour);

		BloodPressure = new JTextField(10);
		BloodPressure.setBounds(frameWidth/2-70, frameHeight/2-100, 161, 36);
		BloodPressure.setFont(smallText);
		BloodPressure.setEditable(flag);
		getContentPane().add(BloodPressure);

		weight = new JTextField(10);
		weight.setBounds(frameWidth/2-70, frameHeight/2-50, 161, 36);
		weight.setFont(smallText);
		weight.setEditable(flag);
		getContentPane().add(weight);

		Description = new JTextArea(5,40);
		Description.setBounds(frameWidth/2-70, frameHeight/2, 600, 100);
		Description.setFont(smallText);
		Description.setLineWrap(true);
		Description.setWrapStyleWord(true);
		Description.setEditable(flag);
		getContentPane().add(Description);

		Diagnosis = new JTextField(10);
		Diagnosis.setBounds(frameWidth/2-70, frameHeight/2+114, 600, 60);
		Diagnosis.setFont(smallText);
		Diagnosis.setEditable(flag);
		getContentPane().add(Diagnosis);
	}
}







