package boundry.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.OperatorContoller;
import ocsf.Main.MainClient;
import email.EmailController;
import entity.Patient;
import boundry.utility.MyJPanel;
/**
 * 
 * @author shay zafran
 *
 */
public class NewPatientGUI extends AbstractGUI {

	
	private static final long serialVersionUID = 1L;
	private JTextField patientID;
	private JTextField f_name;
	private JTextField l_name;
	private JTextField email;
	private JButton save;
	private Patient patient;
	private MyJPanel gPanel;
	private static final String[] gtexts = {"Patient ID", "Name", "Family Name", "Email", "Address","Gender","HMO Name","Height"};
	
	/**
	 * NewPatientGUI constructor,without parameters,defining buttons
	 * Insert details of patient to abstract_person table
	 * Chose the worker_num with random method
	 * Insert details of patient to person table
	 */
	public NewPatientGUI() {
		
		setTitle("New Patient");
            
		
		gPanel=new MyJPanel(gtexts, 0, 0, frameWidth, 100);
		gPanel.setSize(1366, 204);
		gPanel.setLocation(0, 0);
		getContentPane().add(gPanel);
		
		//Define save button.
		save = new JButton("Save");
		save.setFont(bigText);
		save.setBounds(442, 323, 200, 70);
		getContentPane().add(save);
		
		ArrayList<JTextField> gOrder = gPanel.getgOrder();
		
		
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String ans;
				if((ans =OperatorContoller.newPatient(gOrder)) == null){
					setVisible(false);
					new OperatorMainGUI();
				}
				else
					JOptionPane.showMessageDialog(null, ans, "Error", JOptionPane.WARNING_MESSAGE, null);
			}
		});
		
		
		
		//Presses backward button//
		backward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OperatorMainGUI();
				//dispose();
			}});
		
		
		forward.setVisible(false);
		setVisible(true);
		

	}//Constructor NewPatientGUI().
	
		

}