package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import boundry.GUI.OperatorMainGUI;
import boundry.utility.MyJTable;

import com.mysql.jdbc.Statement;

import ocsf.Main.MainClient;
import email.EmailController;
import entity.Patient;

/**
 * 
 * @author shay zafran
 *
 */

public class OperatorContoller {
   
   
	/**
	 * 
	 * @param patient
	 * @param appointmentRefferal
	 */
	public static String newPatient(ArrayList<JTextField> gOrder){
		ArrayList<Integer> arr ;
		int i=0, id;
		double height;
		for (JTextField jtf : gOrder) {
			if(jtf.getText().isEmpty())
				return "one or more of the fields are Empty";
		}
		try{
			id=Integer.parseInt(gOrder.get(0).getText());
		} catch(NumberFormatException e){
			return "Wrong ID";
		}
		try{
		height=Double.parseDouble(gOrder.get(7).getText());
		} catch(NumberFormatException e){
			return "Wrong height";
		}
//		System.out.println(" the id:"+ id+"\nName: "+gOrder.get(1).getText()+"last:"+gOrder.get(2).getText()+"email:"+ gOrder.get(3).getText()+"addrss:"+gOrder.get(4).getText()+" gender: "+gOrder.get(5).getText());
		
		//Insert details of patient to abstract_person table.
		String msg="INSERT INTO abstract_person VALUES("+id+",'" +gOrder.get(1).getText()+"','" +gOrder.get(2).getText()+"','"+gOrder.get(3).getText()+"','"+ gOrder.get(4).getText()+"','"+ gOrder.get(5).getText()+"')"; 

		String msg1="SELECT worker_num FROM user where role='doctor'";
		if(gOrder.get(5).getText().equals("male")==false && gOrder.get(5).getText().equals("female")==false){
			return "Wrong gender field";
		}
		try {	
			//wrong HMO
			String HMOQuery = "SELECT * FROM hmo WHERE HMO = '"+gOrder.get(6).getText()+"'";
			CachedRowSet row = MainClient.mainClient.sendAndWaitForReply(HMOQuery);
			if(row.next() == false)
				return "Wrong HMO field"; 
			
			//patient exist
			String existQuery = "SELECT * FROM patient WHERE personID = "+gOrder.get(0).getText();
			row = MainClient.mainClient.sendAndWaitForReply(existQuery);
			if(row.next() == true)
				return "patientID already exist";

			//insert
			MainClient.mainClient.sendToServer(msg);
			arr = new ArrayList<Integer>();
			row =(CachedRowSet)MainClient.mainClient.sendAndWaitForReply(msg1);
			while(row.next()){
				arr.add(row.getInt(1));
				i++;	
			}
			//Chose the worker_num with random method.
			Random rnd = new Random();
			int index = 0 + rnd.nextInt(arr.size());
			int worker_num=arr.get(index);
			
//			for(int j=0;j<arr.size();j++)
//			{
//				System.out.println(j+"."+arr.get(j)+"\n");
//			}
			//System.out.println("the index:"+ index+"\nthe random worker num:"+ worker_num);
			
			//Insert details of patient to person table.
			String msg2="INSERT INTO patient VALUES("+id+","+worker_num+",'"+gOrder.get(6).getText()+"',"+height+")";
			MainClient.mainClient.sendToServer(msg2);
			EmailController.sendEmail(gOrder.get(3).getText(), "Welcome to Ihealth","Hellow "+ gOrder.get(1).getText()+"\n and welcome to our service.");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "New Patient was added to the Data Base";

	}


	/**
	 * cancelAppointment method that canceling an appointment to the patient by the operator
	 * @param apcID 
	 */
		public static void cancelAppointment(int apcID,String email,int id) {
		boolean result=false;
		Calendar cal=Calendar.getInstance();
		Date date=cal.getTime();
		String msg="delete from operator_appointment_creation where apcID="+apcID;
		 try {
             String message="select apcID from operator_appointment_creation where patientID="+id;
             CachedRowSet row =(CachedRowSet)MainClient.mainClient.sendAndWaitForReply(message);
             while(row.next()){
            	 System.out.println("(opc11) apcID "+ row.getInt(1));
            	if(row.getInt(1)==apcID)result=true;//check if  appointment number belong to the patient and exist in DB.  
             }
             if(result){//If true->Delete from DB,else don't do nothing.
			MainClient.mainClient.sendToServer(msg);
			JOptionPane.showMessageDialog(null,"Message Confirmation About Cancel  Appointment\n"+"Send an email to the customer");//Display message for operator.	
			//EmailController.sendEmail(email,"Confirmation About Cancel  Appointment", "Appointment number "+ apcID +" canceled");
             }
             else JOptionPane.showMessageDialog(null,"The desire appointment id doesn't exist in data base!");//Display message for operator.	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("the message is:"+msg);
		
		
	}

	/**
	 * check if the patient exist in DB.
	 * @param patientID parameter that by him we check if patient is exist in db
	 * @throws Exception 
	 */
	//public static boolean checkPatientID(int patientID) throws Exception {
		public static boolean checkPatientID(String patientID) throws Exception {
		// TODO - implement OperatorContoller.checkPatientID
		
			try{
				Integer.parseInt(patientID);
			}
			catch(NumberFormatException e)
			{
				return false;
			}
		int patientID1=Integer.parseInt(patientID);
		System.out.println("Operator patientID: "+patientID1);  ////console
		String str=null;
	
		str ="SELECT personID FROM patient WHERE personID="+patientID1;
		MainClient mainClient = new MainClient("localhost", 5554);       //////***IP for tests***//
		mainClient.connectAndWait();
		//	CachedRowSet row = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(str);
			CachedRowSet row = (CachedRowSet)mainClient.sendAndWaitForReply(str);
			if(row.next()){
			//int n=row.getInt(1);
			
			System.out.println("(main)patient ID is="+row.getInt(1));
			if(patientID1==row.getInt(1))
			{
				System.out.println("Found");
				return true;
			}
		}//End if
			return false;
		
	}//End method checkPatientID.


	/**
	 * referralIDsearch is searching referral id in db
	 * @param str
	 * @param id of referral
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean referralIDsearch(String str,int id) throws Exception{
		//ArrayList<Integer> element=new ArrayList<>();
		System.out.println("Op refID: "+id);
		System.out.println("Op docSpec: "+str);
		
		String str1=null;
		int i=0;

	//	System.out.println("(operator con)the id is:"+id);
		//System.out.println("(operator con)the query is:"+str);
		//str1="SELECT ReferralID,Status FROM abstract_referral WHERE refPatientID="+id;
	    str1="SELECT ReferralID FROM abstract_referral WHERE refPatientID="+id+" and Status=0 and ReferralId in(select ReferralNum from referral where doc_specialization='"+str+"')";
		
	    
	    MainClient mainClient = new MainClient("localhost", 5554);         //***IP for tests***//
		mainClient.connectAndWait();
	    CachedRowSet row = (CachedRowSet)mainClient.sendAndWaitForReply(str1);
	   
	    if(row.next())return true;
		return false;
	   
	
	
	    
	}		    	
	
	
	
	public static boolean CheckDoctorSpeciality(int id,String str){
		
		String message;
		CachedRowSet row=null;
		boolean flag=false;
		
		System.out.println("(addapointment)the str: "+str);
        message="select distinct Name,ClinicName,ClinicID,worker_num from abstract_person,clinic,user,specialist_doctor,appointment,operator_appointment_creation where cid=ClinicID and ID=userID and worker_num=specDocID and Specialization='"+str+"' and specDocID=docID  and patientID="+id+" and apcID=appID  order by date asc";
        
      	
		MainClient mainClient = new MainClient("localhost", 5554);          //***IP for tests***//
		try {
			mainClient.connectAndWait();
			row = (CachedRowSet) mainClient.sendAndWaitForReply(message);
			flag=row.first();
			
			} 
		
		catch (Exception e1) {
			e1.printStackTrace();
		}


        //check if the quarry is empty:if(not empty)->the patient meet doctor before. 
			if(flag)
				return true;
			 
			//The quarry empty 
			return false;

	}
	

	
	public static boolean CheckCreationAppointment(int appointmentID){//Check Insert details of Creation appointment to DB
		
		String message;
		CachedRowSet row=null;
		boolean flag=false;
		
        message="SELECT * FROM operator_appointment_creation WHERE apcID="+appointmentID;
          	
		MainClient mainClient = new MainClient("localhost", 5554);                //***IP for tests***//
		try {
			mainClient.connectAndWait();
			row = (CachedRowSet) mainClient.sendAndWaitForReply(message);
			flag=row.first();
			} 
		catch (Exception e1) {
			e1.printStackTrace();
		}

			if(flag)
				return true;
	
			return false;

	}
	
	
	public static boolean TestInsertCreationAppointment(int patientID,int docID,int clinicID,String creationDate,String appointmentDate,String appointmentHour){//Insert details creation appointment for test
		int max;
		boolean flag=false;
		
		
		String str="select max(apcID) from operator_appointment_creation";
		CachedRowSet row1;
		
		
		MainClient mainClient= new MainClient("localhost",5554);         //***IP for tests***//
		try {
			
			mainClient.connectAndWait();
			
			row1 = (CachedRowSet)mainClient.sendAndWaitForReply(str);
			row1.next();
			max=(row1.getInt(1)+1);
			
			
	         System.out.println("max is: "+max);
	 		String saveHour="INSERT INTO operator_appointment_creation VALUES("+max+"," +patientID+"," +docID+","+clinicID+",'"+ creationDate+"','"+ appointmentDate+"','"+ appointmentHour+"',-1)";
	 		System.out.println("(APP)saveHour is "+ saveHour);
		
			mainClient.sendToServer(saveHour);
			mainClient.closeConnection();
			if(CheckCreationAppointment(max))flag=true;//Check if the insert successful
			else flag=false;
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return flag;
	}
	

	
	
	
	/**
	 * getReferralid gives the referral id
	 * @param id ,for the id referral
	 * @return id of the referral
	 */
	public static int getReferralid(int id){
		return id;
	}
}