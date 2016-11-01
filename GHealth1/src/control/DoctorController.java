package control;

import java.util.Date;

import javax.sql.rowset.CachedRowSet;

import boundry.GUI.AbstractGUI;
import boundry.GUI.AbstractMenuGUI;
import boundry.GUI.AppointmentGUI;
import boundry.GUI.LoginGUI;
import boundry.utility.UtilltyGUI;
import ocsf.Main.MainClient;
import email.EmailController;
import entity.Appointment;
import entity.Patient;
import entity.Referral;
import entity.TestReferral;
import entity.User;

public class DoctorController{

	/**
	 * find_patient_ID method that finds the patients by id in the database
	 * @param patient_ID
	 * @return row.next
	 */
	public static boolean find_patient_ID(int patient_ID) throws Exception {
		String str=null;

		str = "SELECT personID FROM patient WHERE personID = " + patient_ID;
		CachedRowSet row = MainClient.mainClient.sendAndWaitForReply(str);
//		row = MainClient.mainClient.sendAndWaitForReply(str);
		return row.next();


	}

	/**
	 * generatingReferral method that creating referral and sending email to laboritorian
	 * @param patient -the referral is on him
	 * @param description 
	 * @param speciality-the doctor that the patient got a treatment by him
	 */
	public static void generatingReferral(Patient patient, String description, String speciality) {
		try {
			Referral referral = new Referral(patient.getID(),description,speciality);
			CachedRowSet crs = MainClient.mainClient.sendAndWaitForReply(referral.insertStr());
			int refID = crs.getInt(1);
			String subject = "IHealth - doctor refferal " + refID;
			String message = "attending doctor: " + patient.getDoctorID() + "\nSpeciality: " + speciality;
			EmailController.sendEmail(patient.getEmail(), subject, message);
//			EmailController.sendEmail("asaf11108@gmail.com", subject, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * generatingTestreferral method -kind of test and description and sending email to laboritorian
	 * @param patient - patient that the test result is on him
	 * @param testKind - type of test that the patient does
	 * @param description 
	 */
	public static void generatingTestReferral(Patient patient, String description, String testKind) {
		try {
			TestReferral testReferral = new TestReferral(patient.getID(), description, testKind);
			CachedRowSet crs = MainClient.mainClient.sendAndWaitForReply(testReferral.insertStr());
			int refID = crs.getInt(1);
			String query = "SELECT email FROM abstract_person JOIN user ON Abstract_Person.ID=user.userID  WHERE role = 'laboratorian'";
			crs = MainClient.mainClient.sendAndWaitForReply(query);
			String subject = "IHealth - test refferal " + refID;
			String message = "test kind: " + testKind + "\ndescription: " + description;
			while(crs.next())
//				EmailController.sendEmail("asaf11108@gmail.com", subject, message);
				EmailController.sendEmail(crs.getString(1), subject, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * manageFile method
	 * @param patientID
	 */
	public static void manageFile(int patientID) {

	}

	/**
	 * 
	 * @param medicalfile
	 */
	public static void transfer_specific_info_from_medical_file() {

	}

	/**
	 * 
	 * @param medicalfile
	 */
	public static void transfer_entire_medical_file() {
	}
	
	/**
	 * find_appID gives the appointment id and details of the appointment
	 * @param patient the appointment is on him
	 * @return
	 */
	public static int find_appID(Patient patient){
		String date = UtilltyGUI.displayDate.format(new Date());
		String query = "SELECT apcID FROM operator_appointment_creation "
				+ "WHERE patientID = " + patient.getID() + " AND docID = " + LoginGUI.user.getWorkerNum() + " AND appointmentDate = '" + date + "'";
		try {
			CachedRowSet row = MainClient.mainClient.sendAndWaitForReply(query);
			if(row.next() == false){
				return -1;
			}
			else{
				for (AbstractGUI abstractGUI : AbstractMenuGUI.menuWindows) {
					if(abstractGUI instanceof AppointmentGUI){
						abstractGUI.toFront();
						abstractGUI.requestFocus();
						return -1;
					}
				}
				return row.getInt(1);
			}
				
		} catch (Exception e) {
		}
		return -1;
	}

	/**
	 * NewAppointment method that inserts details of the patient 
	 * @param patient - patient is the patient
	 * @param appoinment - appointment data
	 * @throws Exception 
	 */
	public static void NewAppointment(Patient patient, Appointment appointment) throws Exception {
		//update referral status
		Referral referral = new Referral(patient.getID());
		referral.update("Status", 1);
		
//		//update service time
		
		String str1="select ClinicID_OAC from operator_appointment_creation where apcID="+appointment.getAppID();
		CachedRowSet row = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(str1);
		if(row.next()){	
        String str2="Update clinic set NumOfPatient= NumOfPatient+1 where ClinicID="+row.getInt(1);
        MainClient.mainClient.sendToServer(str2);
		}
		//insert appointment
		appointment.insert();

		//send email to attending doctor
		User specialistDoctor = new User(patient.getDoctorID());
		String subject = "IHealth - report refferal " + referral.ReferralID;
		String message = "Referral was taked care by doctor: " + LoginGUI.user.getWorkerNum()
				+ "\ndescription: " + appointment.getDescription();
		EmailController.sendEmail(specialistDoctor.getEmail(), subject, message);
//		EmailController.sendEmail("asaf11108@gmail.com", subject, message);
		
		//sent email to HMO
		subject = "IHealth - report payment " + appointment.getAppID();
		message = "Patient: "+patient.getID();
		EmailController.sendEmail("ghealthbraude@gmail.com", subject, message);
	}       


}	

