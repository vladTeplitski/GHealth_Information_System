package control;

import java.awt.Color;
import java.awt.Font;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import control.LaboratorianController;
import entity.TestReferral;

import javax.swing.border.BevelBorder;

import ocsf.Main.MainClient;
import ocsf.Main.client_file;
import boundry.GUI.*;
/**
 * 
 * @author Vladislav Teplitski
 *
 */
public class LaboratorianController extends AbstractMenuGUI{

	private static final long serialVersionUID = 1L;
	public static JButton hideRefBtn;
	public static JPanel panel_1;

	/**
	 * checkReferral method that checks referralnum in database
	 * @param ref 
	 * @return
	 * @throws Exception
	 */
	
	public static boolean checkReferral(String ref1) throws Exception  //check if referral exists
	{
		String str=null; //SQL string
		int ref;
		
		try{
		Integer.parseInt(ref1);
		}
		catch (java.lang.NumberFormatException e1) //string exception
		{
				return false;
		} 
		ref=Integer.parseInt(ref1);
		MainClient mainClient = new MainClient("localhost", 5554);        //***IP for tests***//
		mainClient.connectAndWait(); //for tests
		str ="SELECT testReferralNum FROM test_referral WHERE testReferralNum="+ref; //SQL Query
		//connect to DB server
		CachedRowSet row = (CachedRowSet)mainClient.sendAndWaitForReply(str);
		mainClient.closeConnection();//for tests
		if(row.first())  // check if there is no data - not found in DB
			return true;
		
        return false;
	}
	/**
	 * Show_referral_for_medical_examinations 
	 * @param testReferral
	 * @param textarea1 -Test information
	 * @param panel_1
	 * @throws Exception
	 */
	
	public static void Show_referral_for_medical_examinations(TestReferral testReferral,JTextArea textarea1,JPanel panel_1) throws Exception {
		
		//status indication
		String str_status=null;
		if(testReferral.Status)
			str_status="closed";
		else
			str_status="open";   //if 0
		//end status indication
		
		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(204, 204, 255));
		panel_1.setBounds(908, 140, 418, 211);
		
		
		textarea1.setToolTipText("Test information");
		textarea1.setBackground(panelColor);
		textarea1.setTabSize(20);
		textarea1.setAlignmentX(LEFT_ALIGNMENT);
		textarea1.setAlignmentY(CENTER_ALIGNMENT);
		textarea1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		textarea1.setText(
		"Referral Details for medical examination:"+"\nPatient ID:  "+testReferral.patient_id
												   +"\nDate:  "+testReferral.date
												   +"\nStatus:  "+str_status
												   +"\nTest Description:  "+testReferral.description
												   +"\nTest Kind:  "+testReferral.Test_Kind);
		                                          
	
	}
	
	/**
	 * Insert_Test_Result method insert test results
	 * @param testReferral
	 * @throws Exception
	 */
	public static void Insert_Test_Result(TestReferral testReferral) throws Exception {
		
		new insertLabTestResultsGUI(testReferral);   //GUI of lab results insert
	}
	
	public static boolean insertResultsToDB(int refID,String results){
		
		String save_string;
		boolean flag=true;
		
		save_string = "new INSERT INTO `test_result` (`testID`, `testResultReferralID`, `test_result_description`) VALUES(?, "+"'"+refID+"', "+"'"+results+"');";
		
		MainClient mainClient = new MainClient("localhost", 5554);   //***IP for tests***//
		try {
			mainClient.connectAndWait();
			
	        if(CheckTestReferral(refID)){
			mainClient.sendAndWaitForReply(save_string);  //send the query
			save_string ="select testResultReferralID from test_result where testResultReferralID="+refID;
			CachedRowSet row = (CachedRowSet)mainClient.sendAndWaitForReply(save_string);//Check if insert done
			if(row.next())flag=true;
			else flag=false;
	        }
	        else flag=false;
			mainClient.closeConnection();
		} 

		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	
	public static boolean CheckTestReferral(int refID){//Check if test referral exist 
		
		String checkRef;
		boolean flag=false;
		
		MainClient mainClient = new MainClient("localhost", 5554);   //***IP for tests***//
		try {
			
			
			mainClient.connectAndWait();
			checkRef="select testReferralNum from test_referral where testReferralNum="+refID;
			CachedRowSet row1 = (CachedRowSet)mainClient.sendAndWaitForReply(checkRef);//Check if insert done
			mainClient.closeConnection();
			if(row1.next())flag= true;
			else flag= false;
 	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return flag;
		
	}
	
	
	
	public static boolean checkStatus(int refID){
	boolean status=false;
	
	String status_string = "SELECT `Status` FROM abstract_referral  WHERE `ReferralID`="+refID;

	//connect to DB server
	MainClient mainClient = new MainClient("localhost", 5554);   //***IP for tests***//
		
	try {
			mainClient.connectAndWait();
			CachedRowSet row = (CachedRowSet)mainClient.sendAndWaitForReply(status_string);
			row.next();
			status=row.getBoolean(1);
    	
	} catch (Exception e) {
		e.printStackTrace();	}
	
	return status;
	}
	
	
	

	/**
	 * CloseRefferal method -we sign 1 after taking care of referral and close it
	 * @param testReferral
	 * connection with database 
	 */
	public static void CloseRefferal(TestReferral testReferral) {
		
		
		String status_string = "UPDATE abstract_referral SET `status` = 1 WHERE `ReferralID`="+testReferral.ReferralID;
	
						
		//connect to DB server
		try {

		MainClient.mainClient.sendToServer(status_string);

		
		} catch (Exception e) {
			e.printStackTrace();}

	}
	
	public static void file_send(String path,int refID){
		
		String def_path="testfile.jpg";   //default path
		String str;
		
		System.out.println("Lab control path: "+path);
		
		client_file fc = new client_file("localhost", 5553, path);   // use the client file class to send file with port 5553
	
		if(!(path==null))   //if a file was chosen
		{

			str = "fil INSERT INTO `image` (`imageid`, `image`, `imgSerial`) VALUES (?, ?, ?);"+"ref:"+refID+"path:"+def_path; 
			
			MainClient mainClient = new MainClient("localhost", 5554);   //***IP for tests***//
			try {
				mainClient.connectAndWait();
				CachedRowSet row = (CachedRowSet)mainClient.sendAndWaitForReply(str);
				} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public static boolean file_send_check(int refID)
	{
		CachedRowSet row2=null;
		String str = "SELECT imageid FROM image  WHERE imageid="+refID;
		boolean flag=false;
		
		
		MainClient mainClient = new MainClient("localhost", 5554);   //***IP for tests***//
		try {
			mainClient.connectAndWait();
			row2 = (CachedRowSet)mainClient.sendAndWaitForReply(str);
			

		if(row2.next())
			{
				flag=true;
				return flag;
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	
	}
	
	
	
}
