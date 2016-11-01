
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;

import ocsf.Main.MainClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import control.OperatorContoller;
import boundry.GUI.NewPatientGUI;
import junit.framework.TestCase;

public class NewPatientGUITest2 extends TestCase {
	String[] patientFields = {"123401234", "aviv", "kuku", "asaf11108@gmail.com", "tel-aviv", "male", "clalit", "1.7"};
	ArrayList<JTextField> gOrder; 

	@Before
	protected void setUp() throws Exception {
		gOrder = new ArrayList<JTextField>();
		for (String field : patientFields) {
			JTextField jtf = new JTextField();
			jtf.setText(field);
			gOrder.add(jtf);
		}
		try {
			MainClient.main(new String[]{"localhost", "5554"});
			new NewPatientGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	protected void tearDown() throws Exception {
		MainClient.mainClient.closeConnection();
		
	}
	
	@Test
	public void testEmptyFields() {
		//check if there are empty fields
		gOrder.get(0).setText("");
		String expected = "one or more of the fields are Empty";
		assertEquals(expected, OperatorContoller.newPatient(gOrder));
		gOrder.get(0).setText("123401234");
	}
	
	@Test
	public void testWrongID() {
		//check if the id is equal to 123401234
		gOrder.get(0).setText("1234A1234");
		String expected = "Wrong ID";
		assertEquals(expected, OperatorContoller.newPatient(gOrder));
		gOrder.get(0).setText("123401234");
	}
	
	@Test
	public void testWrongHeight() {
		//check if height is 1.7
		gOrder.get(7).setText("A.7");
		String expected = "Wrong height";
		assertEquals(expected, OperatorContoller.newPatient(gOrder));
		gOrder.get(7).setText("1.7");
	}
	
	@Test
	public void testPatientGender() {
		//check if the patient is male
		gOrder.get(5).setText("mele");
		String expected = "Wrong gender field";
		assertEquals(expected, OperatorContoller.newPatient(gOrder));
		gOrder.get(5).setText("male");
	}
	
	@Test
	public void testPatientHMO() {
		//check if hmo=clalit
		gOrder.get(6).setText("clal");
		String expected = "Wrong HMO field";
		assertEquals(expected, OperatorContoller.newPatient(gOrder));
		gOrder.get(6).setText("clalit");
	}
	
	@Test
	public void testPatientExist() {
		//check if patient exist
		gOrder.get(0).setText("309098999");
		String expected = "patientID already exist";
		assertEquals(expected, OperatorContoller.newPatient(gOrder));
		gOrder.get(0).setText("123401234");
	}
	
	@Test
	public void testPatientInsert() {
		//check if insert is success
		String expected = "New Patient was added to the Data Base";
		assertEquals(expected, OperatorContoller.newPatient(gOrder));
		String del = "DELETE FROM patient WHERE personID = "+gOrder.get(0).getText();
		try {
			MainClient.mainClient.sendToServer(del);
			del = "DELETE FROM abstract_person WHERE ID = "+gOrder.get(0).getText();
			MainClient.mainClient.sendToServer(del);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

