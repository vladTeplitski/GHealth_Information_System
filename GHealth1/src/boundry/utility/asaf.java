package boundry.utility;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import ocsf.Main.MainClient;
import ocsf.Main.MainServer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import control.WorkerController;
import boundry.GUI.AppointmentGUI;

public class asaf {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//		new LoginGUI();
		//		new DoctorMenuGUI(null);
		//	new MedicalFileGUI(null);
		//		String s = (String)JOptionPane.showInputDialog(null, "Enter appointment ID", 
		//						"New appointment",JOptionPane.PLAIN_MESSAGE, null, null, null);

		//		new CustomDialog(new JFrame(), "asaf", new DialogDemo(null));
		//		new TestreferralGUI(null, true);
		//	    JFrame frame = new JFrame();
		//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		//	    Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
		//	        { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
		//	    Object columnNames[] = { "Column One", "Column Two", "Column Three" };
		//	    JTable table = new JTable(rowData, columnNames);
		//
		//	    JScrollPane scrollPane = new JScrollPane(table);
		//	    frame.add(scrollPane, BorderLayout.CENTER);
		//	    frame.setSize(300, 150);
		//	    frame.setVisible(true);
		//		try {
		//			EmailController.sendEmail("vl4d89@gmail.com", "Email from GHealth", "User: ghealthbraude@gmail.com\n"
		//					+ "Password: Braude555");
		//		} catch (MessagingException e) {
		//			e.printStackTrace();
		//		}
		//		try {
		//			int waitingTimeService = (int) ((UtilltyGUI.displayHour.parse("09:32").getTime() - UtilltyGUI.displayHour.parse("08:55").getTime())/60000);
		//			System.out.println(waitingTimeService);
		//		} catch (ParseException e) {
		//			e.printStackTrace();
		//		}
		//		  String languages[] = { "Java", "Perl", "Python", "C++", "Basic", "C#" };
		//
		//		JList<String> jlst = new JList<String>(languages);
		//
		//	    JFrame jfrm = new JFrame("Use JList");
		//	    jfrm.setLayout(null);
		//	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//	    
		//
		//	    
		//		JButton j = new JButton();
		//		j.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				
		//				System.out.println(jlst.getSelectedValuesList());
		//			}
		//		});
		//		jfrm.add(j);
		//		JScrollPane jj = new JScrollPane(jlst);
		//		jj.setBounds(0,0,50,50);
		//	    jfrm.add(jj);
		//	    jfrm.setSize(300, 300);
		//	    jfrm.setVisible(true);

		MainClient mainClient;
		MainServer mainServ;
		char[] pass = "12345".toCharArray();
//
//		MainServer.main(args);
//		//mainServ.createConnection("root", "root");
//		//try {
//		//	mainServ.listen();
//		//} catch (IOException e1) {
//		/	// TODO Auto-generated catch block
//		//	e1.printStackTrace();
//	//	}
//		MainServer.main(new String[]{"5554","root", ""});
//		MainClient.mainClient = new MainClient("localhost",5554);
//		try {
//			MainClient.mainClient.connectAndWait();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////
////		//			mainClient.closeConnection();
////		//			mainServ.close();
//		Object expected = null;
//		System.out.println(WorkerController.login("2001", pass));
//		//			assertEquals(expected, WorkerController.login("2001", pass));
		try {
			MainServer.mainServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
