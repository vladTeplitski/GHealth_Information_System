package boundry.GUI;

import javax.sql.rowset.CachedRowSet;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import boundry.utility.UtilltyGUI;
import control.LaboratorianController;
import entity.TestReferral;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import ocsf.Main.MainClient;

/**
 * 
 * @author Vlad Teplitski
 *
 */
public class LaboratorianMenuGUI extends AbstractMenuGUI implements UtilltyGUI{

	private static final long serialVersionUID = 4038650409479680896L;
	private JButton showReferral;
	private JButton insertTestResult;
	private JButton CloseReferral;
	private JButton hideRefBtn;
	private JPanel panel_1;
	private JTextArea textarea1;
	
/**
 * LaboratorianMenuGUI constructor that shows referrals
 * @param testReferral for showing referral for medical examinations
 */
	public LaboratorianMenuGUI(TestReferral testReferral) {
	container.setSize(496, 500);
		

		
		container.setLocation(382, 140);
		setTitle("Laboratorian Menu");
		
		//-- Show Referral --//
		showReferral = new JButton("Show referral for examinations");
		showReferral.setFont(bigText);
		showReferral.setAlignmentX(CENTER_ALIGNMENT);;
		container.add(showReferral);
		
		showReferral.addActionListener(new ActionListener(){
			
			
			/**
			 * actionPerformed is for the clicking button
			 *@param event1 for clicking the showReferral button
			 *
			 */
			public void actionPerformed(ActionEvent event1) 
			{
			
			try {
				
				panel_1.setVisible(true);
				hideRefBtn.setVisible(true);
				LaboratorianController.Show_referral_for_medical_examinations(testReferral,textarea1,panel_1);
				textarea1.setEditable(false);  //lock for editing the text area
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Program error.");  //prompt for error
				e.printStackTrace();
			}		
			
		    }
		});		
		
		container.add(Box.createRigidArea(new Dimension(10,10)));  //space
		
		//Show test referral details//
				
		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(204, 204, 255));
		panel_1.setBounds(908, 140, 775, 211);
		getContentPane().add(panel_1);
		
		textarea1 = new JTextArea();
		panel_1.add(textarea1);
		
		//hide button
		hideRefBtn = new JButton("Hide referral");
		hideRefBtn.setFont(new Font("Times New Roman", Font.BOLD, 32));
		hideRefBtn.setBounds(908, 367, 247, 36);
		getContentPane().add(hideRefBtn);
		hideRefBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event1) 
			{
				hideRefBtn.setVisible(false);
				panel_1.setVisible(false);
			}});		
		
		panel_1.setVisible(false);
		hideRefBtn.setVisible(false);

		//-- END Show Referral --//
		


		//Insert Test Result//
		insertTestResult = new JButton("Insert Test Results");
		insertTestResult.setFont(bigText);
		insertTestResult.setAlignmentX(CENTER_ALIGNMENT);
		container.add(insertTestResult);
		
		insertTestResult.addActionListener(new ActionListener(){
			
			/**
			 * this is a method that insert test result
			 * @param event1 for insert test result
			 */
			
			public void actionPerformed(ActionEvent event1) 
			{
			try {
				
				LaboratorianController.Insert_Test_Result(testReferral);
				
				} 
			catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, "Program error.");  //prompt error
					e.printStackTrace();
				}
		    }
		});		
		container.add(Box.createRigidArea(new Dimension(10,10)));  //space
		
		

		
		//Close Referral//
		CloseReferral = new JButton("Close referral");
		CloseReferral.setFont(bigText);
		CloseReferral.setAlignmentX(CENTER_ALIGNMENT);
		container.add(CloseReferral);
		
	
		JLabel lblLaboratorianMenu = new JLabel("Laboratorian Menu");
		lblLaboratorianMenu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblLaboratorianMenu.setForeground(Color.YELLOW);
		lblLaboratorianMenu.setBounds(382, 106, 237, 20);
		getContentPane().add(lblLaboratorianMenu);
		

		CloseReferral.addActionListener(new ActionListener(){
			
			/**
			 * this method is for closing referral and show that with a message
			 * @param event1 for close referral
			 */
			public void actionPerformed(ActionEvent event1) 
			{
				LaboratorianController.CloseRefferal(testReferral);
				JOptionPane.showMessageDialog(null, "The referral was closed successfully!");
				
				//info for user//
				JLabel lblReferralWasClosed = new JLabel("Referral was closed.");
				lblReferralWasClosed.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblReferralWasClosed.setForeground(Color.BLUE);
				container.add(lblReferralWasClosed);
				
				
				//hide all the options for the user//
				CloseReferral.setVisible(false);
				insertTestResult.setVisible(false);
				showReferral.setVisible(false);
				panel_1.setVisible(false);
				hideRefBtn.setVisible(false);
				
		    }
		});
		
		
		backward.addActionListener(new ActionListener()    //backward button
		  {		

					public void actionPerformed(ActionEvent e) 
					{
						new LaboratorianMainGUI();
						setVisible(false);  //close the window
						
					}
		  });
		
		setVisible(true);
		
		
		//// check if status 1=closed or 0=opened////
		
//		boolean status=false;
//		String status_string = "SELECT `Status` FROM abstract_referral  WHERE `ReferralID`="+testReferral.ReferralID;
//	
//		//connect to DB server
//
//	    try {
//	    	CachedRowSet row = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(status_string);
//	    	row.next();
//	    	status=row.getBoolean(1);
	    	
		
		
		
	    	if(LaboratorianController.checkStatus(testReferral.ReferralID))
	    	{
				JOptionPane.showMessageDialog(null, "The referral is already closed!");
				
				//info for user//
				JLabel lblReferralWasClosed = new JLabel("Referral is closed.");
				lblReferralWasClosed.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblReferralWasClosed.setForeground(Color.BLUE);
				container.add(lblReferralWasClosed);
	    		
	    		
				CloseReferral.setVisible(false);
				insertTestResult.setVisible(false);
				showReferral.setVisible(false);
				panel_1.setVisible(false);
				hideRefBtn.setVisible(false);

	    	}
		} 
//	    catch (Exception e1) 
//	    	{
//	    		JOptionPane.showMessageDialog(null, "Program error."); //prompt error message
//	    		e1.printStackTrace();
//			}

	}
//}

