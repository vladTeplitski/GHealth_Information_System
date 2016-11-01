package boundry.GUI;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import entity.Patient;
import control.OperatorContoller;

import javax.swing.JButton;
/**
 * 
 * @author shay zafran
 *
 *
 */



public class OperatorMainGUI extends AbstractMainGUI {

	private JButton newPatient;
	private static final long serialVersionUID = 1L;

	/**
	 * constructor of OperatorMainGUI without parameters
	 * patient id label and checking if patient is exist and getting a message due to the patient existence
	 */
	public OperatorMainGUI() {
		setTitle("Operator Main");
		
		searchLabel.setText("Patient Id:");
		

		//Presses forward button//
		forward.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e) 
			{
				
				//get the patient id from text field.//

				
				//int id = Integer.parseInt(searchText.getText()); 

				int id=0;
		           //check if patient exist in the DB moving to operator menu,else create administrative case.//
				try {
					
					if(OperatorContoller.checkPatientID(searchText.getText())){
						System.out.println("the patient exist!");
						id = Integer.parseInt(searchText.getText()); 
						Patient patient=new Patient(id);
						setVisible(false);//shutdown the operator maingui window.
						new OperatorMenuGUI(patient);
						
					}
					else {
						int i=0;
						while(id!=0){
							id=id/10;
							i++;
						}
						if(i!=9)JOptionPane.showMessageDialog(null, "Wrong ID!\nMissing digits or have more digits in numner ID  "+i);
						else  JOptionPane.showMessageDialog(null, "The patient does'nt exist in Data Base!\nTo add a new customer to the system pressure on new patient button");
   
					      }
	
				}

				catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		 
			}
		});
		
		//Define new patient JButton//
		newPatient=new JButton("New Patient");
		newPatient.setFont(bigText);
		newPatient.setBounds(550, frameHeight-250, 250, 70);
		getContentPane().add(newPatient);
		
		//presses on New Patient button//
		newPatient.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewPatientGUI();
				setVisible(false);
			}				
		});
		
		setVisible(true);
		}
}
