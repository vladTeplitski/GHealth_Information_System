package boundry.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import boundry.utility.MyJList;
import control.DoctorController;
import entity.Patient;
/**
 * 
 * @author yafit aronovich
 *
 */
public class TestreferralGUI extends AbstractReferralGUI{
	private static final long serialVersionUID = 1L;
	private static final String[] tests = {"blood", "eyes","piss","pregnancy","hearing","ct","Neurologically"};
	private MyJList list;

	/**
	 * 
	 * @param patient is appearance of patient that the test referral is about him
	 * @param testID 
	 * @param flag to edit and the other is for selecting in the combobox
	 * list of tests 
	 * backward and forward buttons
	 */
	public TestreferralGUI(Patient patient) {
		super(patient);
		setTitle("test refferal screen");
		
		list = new MyJList(tests, frameWidth/2, frameHeight/5, 200, 150);
		getContentPane().add(list.getJps());
		
		combo_label.setText("Test kind:");

		//Presses backward button//
		backward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				performMenuClose();
			}});
		forward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DoctorController.generatingTestReferral(patient, desc.getText(), list.getSelectedValuesList().toString());
				performMenuClose();
			}
		});
		
		setVisible(true);

	}

}