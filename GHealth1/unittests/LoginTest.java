import static org.junit.Assert.*;
import junit.framework.TestCase;
import ocsf.Main.MainClient;
import ocsf.Main.MainServer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import control.WorkerController;


public class LoginTest extends TestCase{
	String user = "2001";
	char[] pass = "12345".toCharArray();

	@Before
	protected void setUp() throws Exception {
		try {
			MainClient.main(new String[]{"localhost", "5554"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	protected void tearDown() throws Exception {
		MainClient.mainClient.closeConnection();
		
	}

	@Test
	public void testConnected() {
		//CHECK IF USER=2001 AND PASSWORD IS 12345
		Object expected = null;
		assertEquals(expected, WorkerController.login("2001", pass));
		WorkerController.logout();
	}
	
	@Test
	public void testUserAndPasswordEmpty() {
		//CHECH IF USER_ID & PASSWORD ARE EMPTY
		String expected = "userID and Password is empty";
		assertEquals(expected, WorkerController.login("", "".toCharArray()));
	}
	
	@Test
	public void testUserEmpty() {
		//CHECK IF USERID IS EMPTY AND PASSWORD 12345
		String expected = "UserID is empty";
		assertEquals(expected, WorkerController.login("", pass));
	}
	
	@Test
	public void testPasswordEmpty() {
		//CHECK IF PASSWORD IS EMPTY AND USER =2001
		String expected = "Password is empty";
		assertEquals(expected, WorkerController.login(user, "".toCharArray()));
	}
	
	@Test
	public void testUserNotNumeric() {
		//CHECK IF USERID IS EQUAL TO 2001
		String expected = "Error in userID";
		assertEquals(expected, WorkerController.login("200A", pass));
	}

	@Test
	public void testUserDoesntExist() {
		//CHECK IF USERID EXIST 
		String expected = "user doesn't exist";
		assertEquals(expected, WorkerController.login("2020", pass));
	}
	
	@Test
	public void testWrongPassword() {
		//CHECK IF USERID IS 2001 AND PASSWORD IS EQUAL TO 12345
		String expected = "wrong password";
		assertEquals(expected, WorkerController.login("2001", "123".toCharArray()));
	}
	
	@Test
	public void testAlreadyLogged() {
		//CHECK IF USERID IS 2001 AND PASSORD 12345 SO ITS ALREADY LOGGED
		WorkerController.login("2001", pass);
		String expected = "user is already logged in";
		assertEquals(expected, WorkerController.login("2001", pass));
		WorkerController.logout();
	}
}
