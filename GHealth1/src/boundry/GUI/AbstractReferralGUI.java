package boundry.GUI;

import javax.swing.JTextArea;

import boundry.utility.MyJLabel;
import entity.Patient;

public class AbstractReferralGUI extends AbstractGUI {

	private static final long serialVersionUID = 1L;
	/*defining variables */
	
	protected JTextArea desc;
	protected MyJLabel combo_label;
	protected MyJLabel Description_label;
	

	
	
	/** constructor of AbstractReferralGUI that get patient
	*@param patient that for him the doctor is doing the referral
	*defining ComboBox and his bounds,font 
	*defining  the test_kind bounds,fonts that is in the test referral gui 
	*defining the description label fonts and bounds that is also in test referral gui */
	
	public AbstractReferralGUI(Patient patient) {
		combo_label= new MyJLabel();
		combo_label.setBounds(frameWidth/3+50, frameHeight/4, 161, 36);
		combo_label.setFont(smallText);
		getContentPane().add(combo_label);

		Description_label = new MyJLabel();
		Description_label.setBounds(frameWidth/2-135, frameHeight/2-5, 150,30);
		Description_label.setText("Description:");
		Description_label.setFont(smallText);
		getContentPane().add(Description_label);
		
		desc = new JTextArea(0,0);
		desc.setBounds(frameWidth/2, frameHeight/2, 550, 150);
		desc.setFont(smallText);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		getContentPane().add(desc);
		
	}

}