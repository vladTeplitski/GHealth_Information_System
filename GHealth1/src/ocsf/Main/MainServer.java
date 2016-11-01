package ocsf.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sql.rowset.CachedRowSet;

import ocsf.server.ObservableServer;

import com.sun.rowset.CachedRowSetImpl;

import ocsf.server.ConnectionToClient;
import system.daySysRun;
import system.mountSysRun;
import system.weekSysRun;

/**
 * this class main program of server.
 * @author Group13
 */
public class MainServer extends ObservableServer {
	
	public static MainServer mainServer;
	private static Connection conn;
	private final static int DEFAULT_PORT = 5554;
	public static String path11;
	
	/**
	 * this contractor creates server.
	 * @param port
	 */
	public MainServer(int port) {
		super(port);
	}
	
	/**
	 * main server that can get port by args
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
	    int port = 0; //Port to listen on
	    String user = "";
	    String password = "";
	    
		server_file fs = new server_file(5553);   // new server for file transfer
		fs.start();  //start the server
		
	    try
	    {
	      port = Integer.parseInt(args[0]); //Get port from command line
	      user = args[1];
	      password = args[2];
	    }
	    catch(Throwable t)
	    {
	      port = DEFAULT_PORT; //Set port to 5554
	      user = "root";
	      password = "root";
	    }
		
	    mainServer = new MainServer(port);
	    createConnection(user, password);
	    
	    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new daySysRun(){
	    }, 0, 1, TimeUnit.DAYS);
	    ScheduledExecutorService exec1 = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new weekSysRun(){
	    }, 0, 7, TimeUnit.DAYS);
	    ScheduledExecutorService exec2 = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new mountSysRun(){
	    }, 0, 30, TimeUnit.DAYS);
	    
	    
	   try {
		mainServer.listen();
	} catch (IOException e) {
		e.printStackTrace();
	}

	}

	/**
	 * this method creates connection
	 * @param password 
	 * @param user 
	 */
	public static void createConnection(String user, String password) {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {/* handle the error*/}
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ghealth", user, password);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors*/
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	/**
	 * this method call previous handleMessageFromClient if message is by select server send reply else transfer message to DB for saving
	 */
	protected synchronized void handleMessageFromClient(Object message, ConnectionToClient client){
	    String sql = (String) message;
	    if(sql.charAt(0) == 's' || sql.charAt(0) == 'S'){
	    	//select from DB
	    	try {
	    		CachedRowSet result = executeQuery(sql);
				client.sendToClient(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    }
	    else if(sql.charAt(0) == 'n' || sql.charAt(0) == 'N'){
	    	//id protocol
	    	sql = sql.substring(4, sql.length());
	    	String table = sql.substring(12, sql.indexOf(' ', 12));
	    	String recordID = "SELECT COUNT(*)+1 FROM " + table;
	    	String[] statements = sql.split(";");
	    	try {
	    		CachedRowSet result = executeQuery(recordID);
	    		result.next();
	    		for (String statement : statements) {
	    			PreparedStatement pstmt = conn.prepareStatement(statement);
	    			pstmt.setInt(1, result.getInt(1));
	    			pstmt.executeUpdate();
				}
				client.sendToClient(result);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    }
	    
	    else if(sql.charAt(0) == 'f' || sql.charAt(0) == 'F'){  //file Image
	    	int len;
	    	int index1,index2,index3;
	    	String refID;
	    	String str;
	    	int imageid;
	    	int imgSrial=1;
    		

	    	//divide the SQL string//
    		String str_path;
	    	sql = sql.substring(4, sql.length());  //remove the symbolic "file" for file sql
	    	index1 = sql.lastIndexOf(";");
	    	index2 = sql.lastIndexOf("path:");  //last index of path: symbol
	    	index3= sql.lastIndexOf("ref:");
	    	refID = sql.substring(index3+4, index2);
	    	str_path = sql.substring(index2+5, sql.length());  // the path
	    	
	    	sql = sql.substring(0, index1+1);   // the query
	    	
	    	imageid=Integer.parseInt(refID);
	    	
	    	//end divide//
	    	
	    	//set image serial number (image PK) //
	    	
			str ="SELECT imgSerial FROM image WHERE imageid="+imageid; //SQL Query
    		CachedRowSet check = executeQuery(str);

    		try {
				if(check.last()==true)
					try {
						
						imgSrial=check.getInt(1)+1; //generate next image number, if user wants to add more than 1 image
						
					} catch (SQLException e1) {
						e1.printStackTrace();}
			} catch (SQLException e1) {
				e1.printStackTrace();}

    			
    		
    		//end set image serial number//
	    	
	    	try {
	    		File image = new File(str_path);
	    		FileInputStream fin=new FileInputStream(image);
	    		len=(int)image.length(); 
	    		PreparedStatement ps = conn.prepareStatement(sql);

	    		ps.setInt(1, imageid);
	    	    ps.setBinaryStream(2, fin, len);
	    	    ps.setInt(3, imgSrial);
	    		ps.executeUpdate();    //update DB
	    		
	    		CachedRowSet result=null;
				try {
					client.sendToClient(result);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();}

	    }
	    
    

	    else{
	    	executeStatement(sql);
	    }
	    super.handleMessageFromClient(message, client);
	}
	
	
	protected synchronized void handleMessageFromClient(Object message, ConnectionToClient client,int len,FileInputStream fin)
	{
		
		
	}
	
	
	
	/**
	 * execute statement of sql string
	 * @param sql
	 */
	public static void executeStatement(String sql){
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
	 		stmt.close();
		} catch (SQLException e) {	e.printStackTrace();}
		 		
	}
	
	/**
	 * @param sql
	 * @return CachedRowSet of query that asked by sql string.
	 */
	public static CachedRowSet executeQuery(String sql){
		Statement stmt;
		ResultSet result = null;
		CachedRowSet crs = null;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			
			crs = new CachedRowSetImpl();
			crs.populate(result);
			stmt.close();
		} catch (SQLException e) {	e.printStackTrace();}
		return crs;
	}
	
	public static void setPath(String path)
	{
		path11=path;
	}
	public static String getPath()
	{
		return path11;
	}
	  
}
