package boundry.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import boundry.utility.MyJLabel;
import control.WorkerController;
import entity.User;
import ocsf.Main.MainClient;
import java.awt.Font;
import java.awt.Color;

/**
 * 
 * @author asaf regev
 *
 */
public class LoginGUI extends AbstractGUI {

	private static final long serialVersionUID = -5659151986441621655L;
	private JTextField userID;
	private JPasswordField password;
	public static User user;
	
	/**
	 * login constructor without parameters
	 * in this constructor we define label and textfiled of userID ,password , exit button and connect 
	 */
	public LoginGUI() {
		setTitle("login");
		user = null;
		
		JLabel userID_label = new MyJLabel("UserID:");
		userID_label.setBounds(frameWidth/2-250, frameHeight/2-200, 110, 30);
		getContentPane().add(userID_label);
		
		userID = new JTextField(10);
		userID.setFont(smallText);
		userID.setBounds(frameWidth/2-70, frameHeight/2-200, 161, 36);
		getContentPane().add(userID);
		
		JLabel lblPassword = new MyJLabel("Password:");
		lblPassword.setBounds(frameWidth/2-250, frameHeight/2-150, 142, 50);
		getContentPane().add(lblPassword);
		
		password = new JPasswordField(10);
		password.setBounds(frameWidth/2-70, frameHeight/2-140, 161, 36);
		getContentPane().add(password);

		backward.setText("Exit");
		backward.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(MainClient.mainClient != null)
						MainClient.mainClient.closeConnection();
					dispose();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		forward.setText("Connect");
		
		JLabel lblWelcomeToGhealth = new JLabel("Welcome to GHealth System");
		lblWelcomeToGhealth.setForeground(Color.YELLOW);
		lblWelcomeToGhealth.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblWelcomeToGhealth.setBounds(48, 31, 468, 43);
		getContentPane().add(lblWelcomeToGhealth);
		
		JLabel lblGroup = new JLabel("Group 13");
		lblGroup.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGroup.setForeground(Color.WHITE);
		lblGroup.setBounds(58, 80, 110, 30);
		getContentPane().add(lblGroup);
		forward.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String ans = WorkerController.login(userID.getText(), password.getPassword());
				if(ans == null){
					dispose();
				}
				else{
					userID.setText("");
					password.setText("");
					JOptionPane.showMessageDialog(null, ans, "Error", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
	
		setVisible(true);
	}
}