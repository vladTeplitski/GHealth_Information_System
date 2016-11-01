package boundry.GUI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.LaboratorianController;
import entity.TestReferral;
import ocsf.Main.MainClient;
import ocsf.Main.MainServer;
import ocsf.Main.client_file;

public class FileChooserGUI {
	//public static FileInputStream fin;
	public static String path;
	//public static String path;
	/**
	 * FileChooserGUI constructor opens the option of opening file
	 * @param testReferral
	 * @throws Exception
	 */
	public FileChooserGUI(TestReferral testReferral) throws Exception{
		
	String str;  //for SQL string

	JButton choose_dir= new JButton();
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new java.io.File("D:/Java Projects/"));
    chooser.setDialogTitle("Test results image");
    chooser.showOpenDialog(choose_dir);
    
    	try
    	{
    		File f=chooser.getSelectedFile();  //file
    		path=f.getAbsolutePath();
    	}
    	catch (NullPointerException n1){  // if no file was chosen
    		JOptionPane.showMessageDialog(null, "No file was chosen.");  //prompt message
    	}
    	
    	LaboratorianController.file_send(path,testReferral.ReferralID);  //send file through file client to file server

		chooser.setVisible(false);
		}
	}

