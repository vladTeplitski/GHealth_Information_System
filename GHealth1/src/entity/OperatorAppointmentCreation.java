package entity;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import email.EmailController;
import ocsf.Main.MainClient;

public class OperatorAppointmentCreation {

	private String doctorSpeciality;
	private int doctorID;
	private int clinicID;
	private String creationDate;
	private String appointmentDate;
	private int waitingTimeService;
	private int apcID;
	private int patientID;
	private String appointmentHour;
	private String emailPatient;
	private String NamePatient;
	private String NameClinic;
	


	/**
	 * OperatorAppointmentCreation
	 */
	public OperatorAppointmentCreation(int doctorID,String speciality,Patient patient) {
		this.doctorSpeciality=speciality;
		this.doctorID=doctorID;
		this.patientID=patient.getID();
		System.out.println("(operator appointment)the doctorID"+ doctorID);
		String msg="SELECT cID FROM user where worker_num="+doctorID;
		this.emailPatient=patient.getEmail();
		this.NamePatient=patient.getName();
		try {
	
			CachedRowSet row =(CachedRowSet) MainClient.mainClient.sendAndWaitForReply(msg);
			row.next();
			this.clinicID=row.getInt(1);	
			String msg1="select ClinicName from clinic where ClinicID="+this.clinicID;
			CachedRowSet row1 =(CachedRowSet) MainClient.mainClient.sendAndWaitForReply(msg1);
			row1.next();
			this.NameClinic=row1.getString(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * OperatorAppointmentCreation
	 */

public OperatorAppointmentCreation(int apcID,Patient patient) throws Exception {
	
	String msg="select * from operator_appointment_creation where apcID="+apcID;
	CachedRowSet row =(CachedRowSet) MainClient.mainClient.sendAndWaitForReply(msg);
	while(row.next()){
		this.apcID=row.getInt(1);
		this.patientID=row.getInt(2);
		this.doctorID=row.getInt(3);
		this.clinicID=row.getInt(4);
		this.creationDate=row.getString(5);
		this.appointmentDate=row.getString(6);
		this.appointmentHour=row.getString(7);
		this.waitingTimeService=row.getInt(8);	
	}
				
}


	/**
	 * getCrationDate
	 * @return CrationDate
	 */
	//	public String getCrationDate() {
	//		return this.creationDate;
	//	}

	/**
	 * Create new appointment(insert to DB)
	 * 
	 */
	public void insertAppointmentToDB() {

		int max=0;
		String str="select max(apcID) from operator_appointment_creation";
		CachedRowSet row1;
		try {
			row1 = (CachedRowSet) MainClient.mainClient.sendAndWaitForReply(str);
			row1.next();
			max=(row1.getInt(1)+1);
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         System.out.println("max is: "+max);
		String saveHour="INSERT INTO operator_appointment_creation VALUES("+max+"," +this.patientID+"," +this.doctorID+","+this.clinicID+",'"+ this.creationDate+"','"+ this.appointmentDate+"','"+ this.appointmentHour+"',-1)";
		
		System.out.println("(APP)saveHour is "+ saveHour);
		try {

			MainClient.mainClient.sendToServer(saveHour);
			String msg="select apcID from operator_appointment_creation where patientID="+this.patientID+" and docID="+this.doctorID+" and appointmentDate='"+this.appointmentDate+"'";
			CachedRowSet row =(CachedRowSet) MainClient.mainClient.sendAndWaitForReply(msg);
			if(row.next()){
			JOptionPane.showMessageDialog(null,"Message Queuing successfully\n"+"Send an email to the customer");//Display message for operator.
			//Send email to patient a bout the details of the queue.
			EmailController.sendEmail(this.emailPatient,"Queuing successfully","Dear "+this.NamePatient+",an "+this.doctorSpeciality+" appointment registration was made.\n"+"Details of Queue:\n Appointment ID:"+(row.getInt(1))+"\nDate:"+this.appointmentDate+"\nTime:"+this.appointmentHour+"\nClinic Name:"+this.NameClinic);
		                }
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,"As registration failed ");//Display message for operator.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	/**
	 * getAppointmentDate
	 * @return appointmentDate
	 */
	public String getAppointmentDate() {
		return this.appointmentDate;
	}

	/**
	 * setAppointmentDate
	 * @param appointmentDate
	 */
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate=appointmentDate;
		// TODO - implement OperatorAppointmentCreation.setAppointmentDate
		//		throw new UnsupportedOperationException();
	}
	/**
	 * getWaitingTimeService
	 * @return waitingTimeService
	 */
	public int getWaitingTimeService() {
		return this.waitingTimeService;
	}

	/**
	 * setWaitingTimeService
	 * @param waitingTimeService that is equals to this.waitingTimeService
	 */
	public void setWaitingTimeService(int waitingTimeService) {
		this.waitingTimeService = waitingTimeService;
	}

	/**
	 * OperatorAppointmentCreation
	 * @param patientID
	 */
	public OperatorAppointmentCreation(String patientID) {
		// TODO - implement OperatorAppointmentCreation.OperatorAppointmentCreation
		throw new UnsupportedOperationException();
	}

	/**
	 * setDoctorSpeciality
	 * @param speciality
	 */
	public void setDoctorSpeciality(String speciality) {
		this.doctorSpeciality=speciality;
		// TODO - implement OperatorAppointmentCreation.setDoctorSpeciality
		throw new UnsupportedOperationException();
	}








	public int getApcID() {
		return apcID;
	}








	public void setApcID(int apcID) {
		this.apcID = apcID;
	}






	/**
	 * getCrationDate
	 * @param crationDate
	 */

	public String getCreationDate() {
		return creationDate;
	}






	/**
	 * setCrationDate
	 * @param crationDate
	 */

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}







	/**
	 * getClinicID
	 */
	public int getClinicID() {
		return clinicID;
	}





	/**
	 * set Clinic ID
	 */
	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}


	/**
	 * get Doctor ID
	 */
	public int getDoctorID() {
		return doctorID;
	}


	/**
	 * set Doctor ID
	 */
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	/**
	 * set Appointment Hour
	 */
	public void setAppointmentHour(String appointmentHour){
		this.appointmentHour=appointmentHour;
	}



	/**
	 * get Appointment Hour
	 */
	public String getAppointmentHour(){
		return this.appointmentHour;
	}

}