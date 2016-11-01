package boundry.GUI;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

public abstract class AbstractMenuGUI extends AbstractGUI {

	/*defining variables   */
	
	private static final long serialVersionUID = 380810792193447123L;
	protected JPanel container;
	public static ArrayList<AbstractGUI>menuWindows;
	

	
	/** constructor of AbstractMenuGUI and defining container for border,bounds,color
	  */    
	public AbstractMenuGUI() {
		menuWindows = new ArrayList<AbstractGUI>();
		container = new JPanel();
		container.setBackground(panelColor);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		container.setBorder(loweredbevel);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBounds(frameWidth/2-200, frameHeight/7, 400, 500);
		getContentPane().add(container);
		
		container.add(Box.createRigidArea(new Dimension(20,20)));
		
		forward.setVisible(false);
	}
	
}