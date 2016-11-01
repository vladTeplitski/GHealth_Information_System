package Fixtures;

import java.util.InputMismatchException;

import control.OperatorContoller;
import fit.ActionFixture;

public class newAppointmentCreating extends ActionFixture {
	boolean expected;
	private String doc;
	private int patient_num;
	private int appointmentID;
	private int docID,clinicID;
	
	
	
	public int getDocID() {
		return docID;
	}



	public void setDocID(int docID) {
		this.docID = docID;
	}



	public int getClinicID() {
		return clinicID;
	}



	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}



	public String getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}



	public String getAppointmentDate() {
		return appointmentDate;
	}



	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}



	public String getAppointmentHour() {
		return appointmentHour;
	}



	public void setAppointmentHour(String appointmentHour) {
		this.appointmentHour = appointmentHour;
	}
	private String creationDate,appointmentDate,appointmentHour;
	
	
	
	
	
	
	public boolean isExpected() {
		return expected;		
	}


	
	public void checkPatientID(String patientID) {  //check patient ID in DB
		try {
			System.out.println("patientID: "+patientID);
			try {
				expected = OperatorContoller.checkPatientID(patientID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (InputMismatchException e) {  //if string
			expected = false;
		}
	}
	
	
	public void setAppointmentID(int apcID){
		appointmentID=apcID;
		
	}
	
	public int getAppointmentID(){
		return appointmentID;
	}
	

	
	public void setDocSpec(String str){
		doc=str;
		
	}
	
	public String getDocSpec(){
		return doc;
	}
	
	public void setPatientNum(int x){
		patient_num=x;
	}
	
	public int getPatientNum(){
		return patient_num;
	}
	
	public void referralIDsearch(){  //check referral ID in DB
		try {
			System.out.println("refID: "+getPatientNum());
			System.out.println("DocSpec: "+getDocSpec());
			expected =  OperatorContoller.referralIDsearch(getDocSpec(), getPatientNum());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void AtLeastOneDocSpecVisit(){  //check if patient visited at least one doctor speciality
		
		expected = OperatorContoller.CheckDoctorSpeciality(getPatientNum(),getDocSpec());
			
	}
	
	
	public void CreationAppointment(){//Check if the creation appointment was successful

		expected=OperatorContoller.TestInsertCreationAppointment(getPatientNum(),getDocID(),getClinicID(),getCreationDate(),getAppointmentDate(),getAppointmentHour());
	}
	

}
