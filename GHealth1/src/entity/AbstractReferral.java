package entity;

import java.io.IOException;
import java.util.Date;

import javax.sql.rowset.CachedRowSet;

import boundry.utility.UtilltyGUI;
import ocsf.Main.MainClient;

public abstract class AbstractReferral {

	public int ReferralID;
	public int patient_id;
	public String date;
	public boolean Status;
	public String description;

	/**
	 * AbstractReferral constructor gets details of the referral from db and in the end close connection
	 * @param ref_id num of the referral 
	 * @throws Exception
	 */
	public AbstractReferral(int ref_id) throws Exception {
		String str=null;
		
		//SQL to DB//
		str ="SELECT refPatientID,Date,Status,description FROM abstract_referral WHERE ReferralID="+ref_id;
	    CachedRowSet row = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(str);
		
		row.next();
		ReferralID=ref_id;
		patient_id=row.getInt(1);
		date=row.getString(2);
		Status=row.getBoolean(3);
		description=row.getString(4);
		
		}
	
	/**
	 * AbstractReferral constructor with displaying date
	 * @param patientID is the patient id 
	 * @param description is equals to this.description
	 * @throws Exception
	 */
	public AbstractReferral(int patientID, String description){
		patient_id=patientID;
		Date date = new Date();
		this.date=UtilltyGUI.displayDate.format(date);
		Status=false;
		this.description=description;
	}
	
	public AbstractReferral() {
	}

	/**
	 * insertStr method
	 * @return details of referral into abstract_referral
	 */
	public String insertStr(){
		return "new INSERT INTO abstract_referral VALUES(?, "+patient_id+", '"+date+"', 0, '"+description+"');";
		
	}
	
	public void update(String col, int val) {
		String message = "UPDATE abstract_referral SET "+col+" = "+val+" WHERE ReferralID=" + ReferralID;
		try {
			MainClient.mainClient.sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}