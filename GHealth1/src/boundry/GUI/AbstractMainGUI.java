package boundry.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import control.WorkerController;
import boundry.utility.MyJLabel;

public abstract class AbstractMainGUI extends AbstractGUI {

	/*defining variables*/
	
	private static final long serialVersionUID = 2398559592338042367L;
	protected JLabel searchLabel;
	protected JTextField searchText;

	/**constructor of AbstructMainGUI and creation of logout button
	  */
	public AbstractMainGUI() {
		backward.setText("logout");
		
		/* action listener of the logout button and returning to login screen */
		/*dispose for not opening 2 screens at the same time while running */
		
		 backward.addActionListener(new ActionListener(){	
				public void actionPerformed(ActionEvent e) {
					WorkerController.logout();
					new LoginGUI();
					dispose();
			}
		});
		
		 /*defining label   */
		searchLabel = new MyJLabel();
		searchLabel.setBounds(frameWidth/2-240, frameHeight/2-200, 160, 30);
		getContentPane().add(searchLabel);
		
		searchText = new JTextField(20);
		searchText.setFont(smallText);
		searchText.setBounds(frameWidth/2-70, frameHeight/2-200, 161, 36);
		getContentPane().add(searchText);
	}

}