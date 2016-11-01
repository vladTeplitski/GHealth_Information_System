package ocsf.Main;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import boundry.GUI.LoginGUI;
import boundry.GUI.MedicalFileGUI;
import ocsf.client.ObservableSWRClient;

/**
 * this class is main program of client.
 * @author Asaf
 */
public class MainClient extends ObservableSWRClient {
	
	public static MainClient mainClient;
	private final static int DEFAULT_PORT = 5554;
	public static  int id=5555;
	public static String ip;

	
	/**
	 * this contractor creates client
	 * @param host
	 * @param port
	 */
	public MainClient(String host, int port) {
		super(host, port);
	}
	

	/**
	 * main client that can get host and port by args
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
	    String host = "";
	    int port = 0;  //The port number

	    try
	    {
	      host = args[0];
	      port = Integer.parseInt(args[1]);
	      ip=host;
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	      host = "localhost";  //
	      port = DEFAULT_PORT;
	      ip=host;
	    }
		mainClient = new MainClient(host, port);
		mainClient.connectAndWait();
		
		new LoginGUI();
	}
	
	/**
	 * this method prints all table in result.
	 * @param result
	 */
	public static void printPhysicians(CachedRowSet result)
	{
		try 
		{
	 		while(result.next())
	 		{
				 // Print out the values
				 System.out.println(result.getString(1)+"  " +result.getString(2)+"  " +result.getString(3));
			} 
	 		result.close();
		} catch (SQLException e) {e.printStackTrace();}
	}

}
