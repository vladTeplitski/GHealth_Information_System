
/**
 * 
 */
package boundry.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import control.DoctorController;
import entity.Patient;

/**
 * 
 * @author yafit aronovich
 */
public class DoctorMainGUI extends AbstractMainGUI {

	/*defining static var*/
	private static final long serialVersionUID = -7075299068389875745L;

	/**DoctorMainGUI constructor without parameters 
	 * setting title 
	 * patient id label
	 */
	
	public DoctorMainGUI(){
		setTitle("Doctor Main");
		searchLabel.setText("Patient Id:");

/* ActionListener of the forward button 
 * checking if patient id is ok and the patient is exist*/
		
		forward.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e) 
			{
				int id = 0;
				try{
					id = Integer.parseInt(searchText.getText());
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Error in patientID", "Error", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				try {
					if(DoctorController.find_patient_ID(id)){
						Patient patient = new Patient(id);
						new DoctorMenuGUI(patient);
						dispose();
					}
					else JOptionPane.showMessageDialog(null, "The patient does'nt exist!");


				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		 
			}
		});

		setVisible(true);
	}


}
