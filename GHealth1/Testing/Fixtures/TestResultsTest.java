package Fixtures;

import java.util.InputMismatchException;

import boundry.GUI.OperatorAddAppointmentGUI;
import control.LaboratorianController;
import control.OperatorContoller;
import entity.Patient;
import fit.ActionFixture;

public class TestResultsTest extends ActionFixture {
	boolean expected;
	private String results;
	private int refID;
	private String path;

	
	public boolean isExpected() {  //the boolean expected result
		return expected;		
	}


	
	public void checkRefID(String ref) {  //check ref existence in db
			try {
				expected = LaboratorianController.checkReferral(ref);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void setResults(String results1){  // set the testresults description
		results=results1;
		
	}
	
	public String getResults(){  //get testresults text
		return results;
	}
	
	public void setPath(String path1){  // set path
		path=path1;
		
	}
	
	public String getPath(){  //get testresults text
		return path;
	}
	
	
	public void setRefID(int x){  //set referral id
		refID=x;
		
	}
	
	public int getRefID(){  //get referral id
		return refID;
	}

	
	public void ResultsDescriptionToDB(){//check insert details of test referral to DB
		expected = LaboratorianController.insertResultsToDB(getRefID(), getResults());
	}
	
	public void CheckIfReferral(){//check insert details of test referral to DB
		expected = LaboratorianController.CheckTestReferral(getRefID());
	}
	
	public void checkStatus(int refID){  //check if referral is closed or opened
		expected = LaboratorianController.checkStatus(refID);  //false=opened,true=closed
	}
	public void fileInsertCheck()   //check if image file was inserted into db
	{
		LaboratorianController.file_send(getPath(),getRefID());  //send file through file client to file server
		expected = LaboratorianController.file_send_check(getRefID());
	}
	
}