package boundry.utility;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import boundry.GUI.AbstractGUI;
import boundry.GUI.LoginGUI;
import control.OperatorContoller;
import entity.Patient;
import boundry.GUI.*;

public class ReferralIDSearch extends JFrame implements UtilltyGUI {

	private JButton search;
	private JLabel label;
	private JTextField field;
	//private JPanel mainpanel;
	private  boolean bol;
	//public Patient patient1;
	private JTextField field1;

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ReferralIDSearch constructor
	 * @param patient -the referral is on him
	 * checking if referral is in database
	 * @param abstractGUI 
	 */
	public ReferralIDSearch(Patient patient, AbstractGUI abstractGUI){
		
		setTitle("Search Referral");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setBounds(frameWidth/2-200, frameHeight/2-100, 290, 200);
		
		label=new JLabel("Doctor Speciality");
		
		
		
		
		
		field1=new JTextField();
		
		//field1.setSize(new Dimension(500,220));
		
		search=new JButton("Search");
		search.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) {
			
			int patientID=patient.getID();
				 
				  
			String str=field1.getText();//Take from the field the desire specialty
			
			System.out.println("the str="+str);
			try {
				@SuppressWarnings("unused")
				boolean bol1;
				
				//Check if referral exist in DB.//
				bol1=OperatorContoller.referralIDsearch(str,patientID);
				
                

				    if(bol1){
					System.out.println("The referral exist in DB!!");
					
					new OperatorAddAppointmentGUI(patient,str);	
					abstractGUI.dispose();
					dispose();
				}      
				//If the query  empty//
				else  {   //(element.get(1)==0)
					JOptionPane.showMessageDialog(null, "The desire referral doesn't exist in the system!");
					setVisible(false);
				}
		
			
					

				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 	  
			  }
		});
		
		getContentPane().add(label);
		
		getContentPane().add(field1);
		
		getContentPane().add(search);
		
		
		setVisible(true);

		
		
	}

/**
 * getreferralid 
 * @return bol
 */
	public  boolean getreferralid(){
		return bol;
	}
	
	/**
	 * setreferralif
	 * @param bol2
	 */
public void setreferralif(boolean bol2){
	bol=bol2;
}




}
