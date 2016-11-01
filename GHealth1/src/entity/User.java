package entity;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import ocsf.Main.MainClient;

public class User extends AbstractPerson {

	protected int worker_num;
	protected String role;
	protected int status;
	protected int clinicID;

/**
 * user constructor that get worker num and due to it we have the detail of the worker
 * @param worker_num ,due to it we have the detail of the worker 
 */
	public User(int worker_num) {
		this.worker_num = worker_num;
		String msg = "SELECT * FROM Abstract_Person JOIN user ON user.userID = Abstract_Person.ID WHERE worker_num = " + worker_num;
		try {
			CachedRowSet row = MainClient.mainClient.sendAndWaitForReply(msg);
			row.next();
			ID = row.getInt(1);
			Name = row.getString(2);
			familyName = row.getString(3);
			email = row.getString(4);
			address = row.getString(5);
			gender = row.getString(6);
			role = row.getString(9);
			clinicID = row.getInt(12);
			setStatus(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  User constructor
	 * @param user object from User that gives the details and puts them in variables
	 */
	public User(User user) {
		worker_num = user.worker_num;
		clinicID = user.clinicID;
		role = user.role;
		setStatus(1);
		ID = user.ID;
		Name = user.Name;
		familyName = user.familyName;
		email = user.email;
		address = user.address;
		gender = user.gender;
	}

	/**
	 * setClinicID
	 * @param clinicID is equals to clinicID
	 */
	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}

	/**
	 * getClinicID
	 * @return clinicID
	 */
	public int getClinicID() {
		return this.clinicID;
	}
/**
 * getWorkerNum
 * @return WorkerNum
 */
	public int getWorkerNum() {
		return worker_num;
	}

	/**
	 * setRole
	 * @param role equals to role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * getRole
	 * @return Role
	 */
	public String getRole() {
		return this.role;
	}

	/**
	 * update method
	 * @param password of the worker
	 * @param worker_num 
	 * @param role of the worker
	 * @param i
	 */
	public void update(String column , int i) {
		StringBuilder str = new StringBuilder("UPDATE user SET ").append(column).append(" = ").append(i).append(" WHERE worker_num = ").append(worker_num);
		try {
			MainClient.mainClient.sendToServer(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getStatus
	 * @return  status
	 */
	public int getStatus() {
		return status;
	}
/**
 * setStatus
 * @param status equals to Status
 */
	public void setStatus(int status) {
		this.status = status;
	}
}