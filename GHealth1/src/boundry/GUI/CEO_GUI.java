package boundry.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ocsf.Main.MainClient;
import boundry.utility.ReferralIDSearch;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class CEO_GUI extends AbstractGUI{

	private static final long serialVersionUID = 1L;

	private JButton search;
	private JTextField  clinicID;

	/**
	 * 
	 * CEO_GUI constructor without parameters that search If there is any such clinic if does have that clinic,prints the clinicID else prints An appropriate message
	 * the backward button return to login GUI 
	 */
	public CEO_GUI() {
		
	
		clinicID = new JTextField();
		clinicID.setBounds(595, 367, 129, 26);
		getContentPane().add(clinicID);
		clinicID.setColumns(10);
		
		JButton search = new JButton("Search Clinic");
		search.setBounds(587, 443, 111, 23);
		getContentPane().add(search);
		
		JLabel lblNewLabel = new JLabel("Clinic number:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(412, 367, 182, 23);
		getContentPane().add(lblNewLabel);
		
		//presses on search button
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int clinicid=Integer.parseInt(clinicID.getText());
		   String msg="select ClinicID from clinic where ClinicID="+clinicid;
		   System.out.println("(ceogui) ckinic id: "+clinicid);
		   
		   try {
			CachedRowSet row = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(msg);
			
			if(row.next()) new ClinicManegerGUI(clinicid);
			else JOptionPane.showMessageDialog(getContentPane(),"No such Clinic!\n Try again");
		} catch (Exception e) {
			e.printStackTrace();
		}
		   
		   
			}	
		});
		

		
		backward.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginGUI();
				setVisible(false);
			}				
		});
		
		forward.setVisible(false);
		setVisible(true);
		

	}
}