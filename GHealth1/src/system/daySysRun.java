package system;

import java.util.Calendar;

import javax.sql.rowset.CachedRowSet;

import email.EmailController;
import ocsf.Main.MainServer;
import boundry.utility.UtilltyGUI;

public class daySysRun implements Runnable,UtilltyGUI {

	public void sendingMemoEmail() {
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1); 
		String query = "SELECT patientID, docID, ClinicID_OAC, appointmentDate, appointmentHour FROM operator_appointment_creation "
				+ "WHERE appointmentDate='"+displayDate.format(cal.getTime())+"'";
		CachedRowSet apc;
		try {
			apc = MainServer.executeQuery(query);
			while (apc.next()) {
				int id = apc.getInt(1);
				query = "SELECT email FROM Abstract_Person JOIN patient ON patient.personID = Abstract_Person.ID  WHERE personID = " + id;
				CachedRowSet emailData = MainServer.executeQuery(query);
				emailData.next();
				String subject = "IHealth - reminder for appointment";
				String message = "You have appointment tomorrow.\nDoctor: "+apc.getInt(2)+"\nClinic: "+apc.getInt(3); 
				EmailController.sendEmail(emailData.getString(1), subject, message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		sendingMemoEmail();
	}

}