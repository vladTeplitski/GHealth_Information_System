package boundry.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;

import boundry.utility.UtilltyGUI;
/**
 * 
 * @author dell
 *
 */
public abstract class AbstractGUI extends JFrame implements UtilltyGUI {

	/*defining variables */
	
	private static final long serialVersionUID = -58465270414990084L;
	protected JButton backward;
	protected JButton forward;
	
    
    /**constructor of AbstractGUI without parameters and defining color&size of the screen
	*@param backward return button
	*@param forward next button 
	*/
	public AbstractGUI() {
		getContentPane().setBackground(frameColor);
		getContentPane().setLayout(null);
		setExtendedState( getExtendedState()|JFrame.MAXIMIZED_BOTH );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*defining the back button of our screen ,font and bounds */
		 
		backward = new JButton("Back");
		backward.setFont(bigText);
		backward.setBounds(0, frameHeight-70, 200, 70);
		getContentPane().add(backward);
		
		/*defining the next button of our screen ,font and bounds */
		
		forward = new JButton("Next");
		forward.setFont(bigText);
		forward.setBounds(frameWidth-200, frameHeight-70, 200, 70);
		getContentPane().add(forward);
	}
	
	/**
	 * performMenuClose close window menu
	 */
	public void performMenuClose(){
		AbstractMenuGUI.menuWindows.remove(this);
		dispose();
	}
	
	/**
	 * performMedicalFileClose close window kind of medical
	 */
	public void performMedicalFileClose(){
		MedicalFileGUI.medicalFileWindows.remove(this);
		dispose();
	}
	
}