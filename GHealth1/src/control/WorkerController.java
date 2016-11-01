package control;
import java.util.Arrays;

import javax.sql.rowset.CachedRowSet;

import boundry.GUI.CEO_GUI;
import boundry.GUI.ClinicManegerGUI;
import boundry.GUI.DoctorMainGUI;
import boundry.GUI.LaboratorianMainGUI;
import boundry.GUI.LoginGUI;
import boundry.GUI.OperatorMainGUI;
import ocsf.Main.MainClient;
import entity.SpecialistDoctor;
import entity.User;
/**
 * 
 * @author Group13
 *
 */
public class WorkerController {

	/**
	 * login method worker enters password and id for login to the system
	 * @param userID the id of the worker
	 * @param password the password of the worker
	 * @return null
	 */
	public static String login(String userID, char[] password) {
		if(userID.isEmpty() && password.length == 0)
			return "userID and Password is empty";
		if(userID.isEmpty())
			return "UserID is empty";
		if(password.length == 0)
			return "Password is empty";
		int worker_num = 0;
		try{
			worker_num = Integer.parseInt(userID);
		}catch(NumberFormatException e1){
			return "Error in userID";
		}
		String str = "SELECT Password, status FROM user WHERE worker_num = " + worker_num;
		try {
			CachedRowSet row = MainClient.mainClient.sendAndWaitForReply(str);
			if(row.next() == false)
				return "user doesn't exist";
			if(!Arrays.equals(password, row.getString(1).toCharArray()))
				return "wrong password";
			if(row.getInt(2) == 0)
				createGUI(worker_num);
			else if(row.getInt(2) == 1)
				return "user is already logged in";
			else
				return "user is looked";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * logout
	 */
	public static void logout() {
		LoginGUI.user.update("status", 0);
	}

	/**
	 * createGUI method -what is the role of the worker 
	 * @param id is the id of the worker
	 */
	private static void createGUI(int worker_num) {
		LoginGUI.user = new User(worker_num);
		LoginGUI.user.update("status", 1);
		switch(LoginGUI.user.getRole()){
		case "operator":
			new OperatorMainGUI();
			break;
		case "doctor":
			LoginGUI.user = new SpecialistDoctor(LoginGUI.user);
			new DoctorMainGUI();
			break;
		case "laboratorian":
			new LaboratorianMainGUI();
			break;
		case "clinic_manager":
			String str="select cID from user where worker_num="+worker_num;
			CachedRowSet row;
			try {
				row = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(str);
				row.next();
				new ClinicManegerGUI(row.getInt(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "HMO_maneger":
			new CEO_GUI();
			break;
		}
	}

}