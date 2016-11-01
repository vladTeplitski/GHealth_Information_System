package boundry.GUI;

import javax.sql.rowset.CachedRowSet;
import javax.swing.Box;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.JLabel;

import control.LaboratorianController;
import ocsf.Main.MainClient;
import entity.TestReferral;

import java.awt.Font;

public class insertLabTestResultsGUI extends AbstractMenuGUI {

	/**
	 * defining variables
	 */
	private static final long serialVersionUID = 1L;
	private JTextField results_field;
	private JTextField textField_ref;
	private JTextField txtAddATest;
	
	/**
	 * this is constructor of insertLabTestResultsGUI that gets testReferral
	 * @param results_field is a screen with designs for the Laboratorian
	 * @param lblReferralId is label with fonts,color
	 * @param textField_ref is a TextField ReferralID
	 * @param lblTestIdIs is a label with a message
	 * @param btnSaveResults is a button of saving
	 * @param lblNewLabel is a label with message
	 * @param txtAddATest is a TextField to add test image
	 * */
	
	public insertLabTestResultsGUI(TestReferral testReferral){
		
		//insert laboratory test results GUI details
		
		container.setSize(688, 414);
		container.setLocation(288, 188);
		
		JTextArea txtrTestResultsDescription = new JTextArea();
		txtrTestResultsDescription.setFont(new Font("Times New Roman", Font.BOLD, 23));
		container.add(txtrTestResultsDescription);
		
		container.add(Box.createRigidArea(new Dimension(10,10)));  //space
		
		results_field = new JTextField();
		results_field.setFont(new Font("Tahoma", Font.PLAIN, 25));
		results_field.setBackground(new Color(255, 255, 0));
		results_field.setText("Laboratorian, please fill the form with the test results:");
		results_field.setBounds(288, 138, 688, 34);
		getContentPane().add(results_field);
		results_field.setColumns(10);
		results_field.setEditable(false);  //lock for editing
		
		JLabel lblReferralId = new JLabel("Test results for referral number:");
		lblReferralId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblReferralId.setForeground(Color.WHITE);
		lblReferralId.setBounds(288, 102, 555, 20);
		getContentPane().add(lblReferralId);
		
		textField_ref = new JTextField();
		textField_ref.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_ref.setText(""+testReferral.ReferralID);   //the referral number
		textField_ref.setColumns(10);
		textField_ref.setBounds(798, 99, 178, 26);
		getContentPane().add(textField_ref);
		textField_ref.setEditable(false);  //lock for editing
		
		JLabel lblTestIdIs = new JLabel("* The test ID is generated automatically.");
		lblTestIdIs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTestIdIs.setForeground(new Color(255, 255, 0));
		lblTestIdIs.setBounds(1015, 446, 421, 20);
		getContentPane().add(lblTestIdIs);
		
		JButton btnSaveResults = new JButton("Save results");
		btnSaveResults.setFont(new Font("Times New Roman", Font.BOLD, 32));
		btnSaveResults.setBounds(1118, 552, 200, 50);
		getContentPane().add(btnSaveResults);
		
		JLabel lblImportantNotes = new JLabel("Important notes:");
		lblImportantNotes.setForeground(Color.GREEN);
		lblImportantNotes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblImportantNotes.setBounds(1015, 414, 231, 20);
		getContentPane().add(lblImportantNotes);
		
		JLabel lblClicksave = new JLabel("* Click \"Save results\" to store the results in the data base. ");
		lblClicksave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClicksave.setForeground(Color.YELLOW);
		lblClicksave.setBounds(1015, 470, 531, 34);
		getContentPane().add(lblClicksave);
		
		JLabel lblNewLabel = new JLabel("* You won't be able to edit the results after saving.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBounds(1015, 508, 531, 26);
		getContentPane().add(lblNewLabel);
		
	    txtAddATest = new JTextField();
	    txtAddATest.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    txtAddATest.setText("Add a test image:");
	    txtAddATest.setEditable(false);
	    txtAddATest.setColumns(10);
	    txtAddATest.setBackground(Color.YELLOW);
	    txtAddATest.setBounds(1015, 138, 402, 34);
	    getContentPane().add(txtAddATest);
	    
	    /*Add test image*/
	    JButton btnBrowse = new JButton("Browse image");
	    btnBrowse.setFont(new Font("Times New Roman", Font.BOLD, 32));
	    btnBrowse.setBounds(1015, 188, 233, 43);
	    getContentPane().add(btnBrowse);
	    btnBrowse.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event1)
	    	{
	    		try {
					new FileChooserGUI(testReferral);
			
					} 
	    		catch (Exception e) 
	    		{
	    			JOptionPane.showMessageDialog(null, "Program error."); //Prompt for program error
					e.printStackTrace();
					
				}	    		
	    	}
	    });
		

		btnSaveResults.addActionListener(new ActionListener(){
			
			/* defining variables , saving the results and prints them
			 * if we don't get any result a window with "no results" is opened
			 * else we saving the results */
			
			public void actionPerformed(ActionEvent event1) 
			{
				boolean flag=false;
				String results=null;
				//String save_string=null;
				results=txtrTestResultsDescription.getText();
				
				if(txtrTestResultsDescription.getText().isEmpty())   // if the results field is empty
					JOptionPane.showMessageDialog(null, "No results entered.\nPlease fill the results text field!");
				
				else // save the results //
				{  

					/*DB INSERT SQL*/
					//save_string = "new INSERT INTO `test_result` (`testID`, `testResultReferralID`, `test_result_description`) VALUES(?, "+"'"+testReferral.ReferralID+"', "+"'"+results+"');"; 
					
					flag=LaboratorianController.insertResultsToDB(testReferral.ReferralID, results);  //insert succeeded or not				
					/*connect to DB server*/
					
//				    try 
//				    	{
//				    	CachedRowSet row = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(save_string);
//				    	} 
//				    catch (Exception e) 
//				    	{
//						JOptionPane.showMessageDialog(null, "Program error."); //Prompt for program error
//						e.printStackTrace();
//						}
					
					
				    if(flag)
				    	JOptionPane.showMessageDialog(null, "The results are saved successfuly!");  //prompt a message
				    else 
				    	JOptionPane.showMessageDialog(null, "Problem saving results in database!");  //prompt a message
					
				    btnSaveResults.setVisible(false);
					txtrTestResultsDescription.setEditable(false);  //lock test area

				}
		    }
		});

	/*close window on end*/
	
	backward.addActionListener(new ActionListener()    //backward button
	  {		
				public void actionPerformed(ActionEvent e) 
				{
					setVisible(false);  //close the window
				}
	  });
	
	setVisible(true);
	
	}
}
