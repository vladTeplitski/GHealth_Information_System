package entity;

import javax.sql.rowset.CachedRowSet;

import boundry.GUI.LoginGUI;
import ocsf.Main.MainClient;

public class TestReferral extends AbstractReferral {

	public String Test_Kind;

	/**
	 * TestReferral constructor that gets test kind due to referral id from db
	 * @param ref is the referral 
	 * @throws Exception
	 */
	
	public TestReferral(int ref) throws Exception{
		super(ref);
		String str=null;
		//SQL to DB//
		str ="SELECT Test_Kind FROM test_referral WHERE testReferralNum="+this.ReferralID;
	    //MainClient mainclient3=new MainClient("localhost",5554);
	    //mainclient3.connectAndWait();
		CachedRowSet row = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(str);
		row.next();  //next row
		Test_Kind=row.getString(1);  // save test kind to a String

	}
	/**
	 *  TestReferral constructor
	 * @param patientID is the patient id
	 * @param description is the description of the doctor about the patient
	 * @param testKind is the kind of test that the patient did
	 * @throws Exception
	 */
	public TestReferral(int patientID, String description, String testKind) throws Exception{
		super(patientID, description);
		Test_Kind=testKind;
	}
	
	@Override
	/**
	 * insertStr
	 * @return to test_referral in db the kind of test
	 */
	public String insertStr(){
		return super.insertStr()+"INSERT INTO test_referral VALUES(?, '"+Test_Kind+"',"+LoginGUI.user.worker_num+")";
	}

}