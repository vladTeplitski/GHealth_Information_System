package boundry.utility;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyJPanel extends JPanel implements UtilltyGUI {

	//defining variables
	private static final long serialVersionUID = 1L;
	private ArrayList<JTextField> gOrder;
	
	/**
	 * MyJPanel constructor
	 * @param gtexts
	 * @param x
	 * @param y
	 * @param width 
	 * @param height
	 */
	public MyJPanel(String[] gtexts, int x, int y, int width, int height) {
		gOrder = new ArrayList<JTextField>();
		setBackground(frameColor);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		for (String text : gtexts) {
			createtSmallPanel(text);
		}
		setBounds(x, y, width, height);
		
	}

	private void createtSmallPanel(String text) {
		JPanel smallPanel = new JPanel();
		smallPanel.setBackground(frameColor);
		smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
		JLabel label = new MyJLabel();
		label.setText(text);
		label.setFont(bigText);
		smallPanel.add(label);
		JTextField field = new JTextField(10);
		field.setFont(smallText);
		smallPanel.add(field);
		gOrder.add(field);
		add(smallPanel);
	}
	
	/**
	 * getgOrder
	 * @return  gOrder
	 */
	public ArrayList<JTextField> getgOrder() {
		return gOrder;
	}
	
	/**
	 *  setgOrder
	 * @param gOrder
	 */
	public void setgOrder(ArrayList<JTextField> gOrder) {
		this.gOrder = gOrder;
	}

}
