package entity;

import javax.sql.rowset.CachedRowSet;

import boundry.GUI.LoginGUI;
import ocsf.Main.MainClient;

public class Referral extends AbstractReferral {

	private String doc_specialization;

	/**
	 * Referral constructor gets due to referral id from db ,the details of a referral
	 * @param patientid the id of a patient
	 * @param description about referral
	 * @param doc_specialization to doctor
	 */
	public Referral(int patient_id, String description,String doc_specialization){
		super(patient_id, description);
		this.doc_specialization=doc_specialization;
	}
		
	/**
	 * Referral constructor that gets details about referral
	 * @param patientid is the id of a patient that the referral is reffer to
	 */
	public Referral(int patientid) {
		super();
		doc_specialization = ((SpecialistDoctor)LoginGUI.user).getSpecialization();
		String str ="SELECT * FROM abstract_referral JOIN referral ON ReferralID=ReferralNum "
				+ "WHERE refPatientID="+patientid+" AND Status=0 "
						+ "And doc_specialization='"+ doc_specialization+"'";
		System.out.println(str);
		CachedRowSet row;
		try {
			row = MainClient.mainClient.sendAndWaitForReply(str);
			row.next();
			ReferralID=row.getInt(1);
			this.patient_id = patientid;
			date=row.getString(3);
			Status=row.getBoolean(4);
			description = row.getString(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDoc_specialization() {
		return doc_specialization;
	}

	public void setDoc_specialization(String doc_specialization) {
		this.doc_specialization = doc_specialization;
	}

	@Override
	/**
	 * insertStr
	 * @return to referral in db the kind of specialty
	 */
	public String insertStr(){
		return super.insertStr()+"INSERT INTO referral VALUES(?, '"+doc_specialization+"')";
		
	}


}