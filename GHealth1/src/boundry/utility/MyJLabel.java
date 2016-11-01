package boundry.utility;

import java.awt.Color;

import javax.swing.JLabel;

public class MyJLabel extends JLabel implements UtilltyGUI {

	private static final long serialVersionUID = -7454078473969472524L;

	public MyJLabel() {
		this(null);
	}
	
	
	/**
	 * MyJLabel sets font and color
	 * @param text
	 */
	
	public MyJLabel(String text) {
		super(text);
		setFont(bigText);
		setForeground(Color.WHITE);
	}
	
	

}
