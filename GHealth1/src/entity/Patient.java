package entity;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import ocsf.Main.MainClient;


public class Patient extends AbstractPerson {

	private String HMOName;
	private int doctorID;
	private double height;

	
	public Patient(int id) {
		this.ID = id;
		String msg = "SELECT * FROM Abstract_Person JOIN patient ON patient.personID = Abstract_Person.ID  WHERE personID = " + id;
		try {
			
			CachedRowSet row =(CachedRowSet) MainClient.mainClient.sendAndWaitForReply(msg);
			row.next();
			Name = row.getString(2);
			familyName = row.getString(3);
			email = row.getString(4);
			address = row.getString(5);
			gender = row.getString(6);
			setDoctorID(row.getInt(8));
			setHMOName(row.getString(9));
			setHeight(row.getDouble(10));
	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String getClinicName() {
		// TODO - implement Patient.getClinicName
		throw new UnsupportedOperationException();
	}

	public String getHMOName() {
		return HMOName;
	}

	public void setHMOName(String hMOName) {
		HMOName = hMOName;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int i) {
		this.doctorID = i;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}