package entity;

import java.io.IOException;

import javax.sql.rowset.CachedRowSet;

import ocsf.Main.MainClient;

public class Appointment {

	private int appID;
	private String date;
	private String hour;
	private Double weight;
	private String BloodPresure;
	private String description;
	private String diagnosis;

	public Appointment(int appID) {
		this.appID = appID;
		String message = "SELECT * FROM appointment WHERE appID = " + appID;
		CachedRowSet row;
		try {
			row = MainClient.mainClient.sendAndWaitForReply(message);
			row.next();
			this.date = row.getString(2);
			this.hour = row.getString(3);
			this.weight = row.getDouble(4);
			this.BloodPresure = row.getString(5);
			this.description = row.getString(6);
			this.diagnosis = row.getString(7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Appointment(int appID, String date, String hour, String weight,
			String bloodPresure, String description, String diagnosis) {
		this.appID = appID;
		this.date = date;
		this.hour = hour;
		setWeight(weight);
		BloodPresure = bloodPresure;
		this.description = description;
		this.diagnosis = diagnosis;
	}


	/**
	 * setDate
	 * @param date is equals to this.date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * getDate of the appointment
	 * @return date
	 */
	public String getDate() {
		return this.date;
	}

	/**
	 * setHour
	 * @param hour - set hour
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**
	 * getHour hour of the appointment
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * setBloodPressue
	 * @param pressure 
	 */
	public void setBloodPressue(String pressure) {
		this.BloodPresure = pressure;
	}

	/**
	 * getBloodPressue of the patient
	 * @return BloodPressue
	 */
	public String getBloodPressue() {
		return this.BloodPresure;
	}

	/**
	 * setWeight
	 * @param Weight that is equals to this.Weight
	 */
	public void setWeight(String weight) {
		if(weight.isEmpty())
			this.weight = 0.0;
		else
			this.weight = Double.parseDouble(weight);
	}

	/**
	 * getWeight gets the weight of the patient
	 * @return height of patient
	 */
	public Double getWeight() {
		return this.weight;
	}

	/**
	 * setDescription that is equals to this.description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getDescription gets the description,opinion of the doctor
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * setDiagnosis
	 * @param diagnosis that is equals to this.diagnosis
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * getDiagnosis -the doctor is writing his diagnosis
	 * @return diagnosis
	 */
	public String getDiagnosis() {
		return this.diagnosis;
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}


	public void insert() {
		String query = "insert into appointment VALUES("+ appID + ",'"+date+"','"+hour+"',"+weight+",'"+BloodPresure+"','"+description+"','"+diagnosis+"')";
		try {
			MainClient.mainClient.sendToServer(query);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}