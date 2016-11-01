package entity;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import ocsf.Main.MainClient;

public class SpecialistDoctor extends User {

	private String specialization;

	/**
	 * SpecialistDoctor constructor that gets the specialization of the doctor from db
	 * @param user -the user is the SpecialistDoctor
	 */
	public SpecialistDoctor(User user) {
		super(user);

		String msg = "SELECT Specialization FROM specialist_doctor WHERE specDocID = " + worker_num;

		try {
			CachedRowSet row = MainClient.mainClient.sendAndWaitForReply(msg);
//			row = MainClient.mainClient.sendAndWaitForReply(msg);
			row.next();
			specialization = row.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getSpecialization
	 * @return Specialization
	 */
	public String getSpecialization() {
		return this.specialization;
	}

	/**
	 * setSpecialization
	 * @param specialization is equals to specialization
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}


}