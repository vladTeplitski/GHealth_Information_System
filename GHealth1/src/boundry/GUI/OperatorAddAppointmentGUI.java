package boundry.GUI;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.OperatorContoller;
import boundry.utility.CalendarProgram;
import boundry.utility.MyJTable;
import ocsf.Main.MainClient;
import entity.OperatorAppointmentCreation;
import entity.Patient;

/**
 * 
 * @author shay zafran
 *
 */
public class OperatorAddAppointmentGUI extends AbstractGUI  {

	private static final long serialVersionUID = 1L;
	
	private JTable doctorSpeciality;
	private JTable clinicChoose;
	private JTable dateChoose;
	private Patient patient;
	private int refferalID;
	private JLabel specitiallity;
	private OperatorAppointmentCreation operatorAppointmentCreation;
	private JTextField specialistDoctor;
	private JTable table;
	private MyJTable addappointments;
	public MyJTable addappointments1;
	public MyJTable addappointments2;
	public static OperatorAddAppointmentGUI oper;
	private OperatorAppointmentCreation appCreation;
	
	
	/**
	 * OperatorAddAppointmentGUI constructor takes details of appointment to make one
	 * @param patient the patient that the appointment is for him
	 * @param refferalID
	 * @throws Exception 
	 */
	//public OperatorAddAppointmentGUI(Patient patient, int refferalID,String str) throws Exception {
		public OperatorAddAppointmentGUI(Patient patient,String str) throws Exception {
		boolean flag,flag2;
		ReentrantLock lock=new ReentrantLock(); 
		
		String message=null;
		
		System.out.println("(addapointment)the str: "+str);
        message="select distinct Name,ClinicName,ClinicID,worker_num from abstract_person,clinic,user,specialist_doctor,appointment,operator_appointment_creation where cid=ClinicID and ID=userID and worker_num=specDocID and Specialization='"+str+"' and specDocID=docID  and patientID="+patient.getID()+" and apcID=appID  order by date asc";
		
		flag=OperatorContoller.CheckDoctorSpeciality(patient.getID(),str);
		//CachedRowSet row =(CachedRowSet) MainClient.mainClient.sendAndWaitForReply(message);
        //check if the quarry is empty:if(not empty)->the patient meet doctor before. 
		if(flag)
         addappointments = MyJTable.newTable(message, 100, 100, 400, 400);
        //The quarry empty
		else{ 
			message="select Name,ClinicName,cID,worker_num from user,specialist_doctor,abstract_person,clinic where Specialization='"+str+"' and specDocID=worker_num and userID=ID and cID=ClinicID";
				addappointments = MyJTable.newTable(message, 100, 100, 500, 400);
            }
		
		getContentPane().add(addappointments.getJps());
		
		/**
		 * mousePressed
		 * @param me 
		 */
		addappointments.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				try{
					JTable table =(JTable) me.getSource();
					Point p = me.getPoint();
					int row = table.rowAtPoint(p);
					Runnable lockingThread;
					if (me.getClickCount() == 2){
					 int docid=(int)table.getModel().getValueAt(row, 3);
					 System.out.println("(operatorAdd) docid"+ docid +"spec"+ str +"pid"+ patient.getID());
					 appCreation=new OperatorAppointmentCreation(docid,str,patient); 
					 
					 
					 
					 //Table of the hours of creation appointment//
                     lockingThread = new Runnable(){	 
					@Override
					/**
					 * run method is taking appointment date &hour and show a message
					 *
					 */
					public void run()
                       {
					
				 String message1="SELECT appointmentHour FROM operator_appointment_creation where docID="+docid+" and appointmentDate='"+appCreation.getAppointmentDate()+"' order by appointmentHour asc";
				 addappointments1=MyJTable.newTable1(message1, 650, 100, 400, 400);
			     getContentPane().add(addappointments1.getJps());
			     
			     /**
			      * here we choose the desire hour
			      */
			     //Chose the desire hour from table.
			     addappointments1.addMouseListener(new MouseAdapter() {
			    	 public void mousePressed(MouseEvent me1) {
			    		    
							JTable table1 =(JTable) me1.getSource();
							Point p1 = me1.getPoint();
							int row1 = table1.rowAtPoint(p1);
							if (me1.getClickCount() == 2){
							String hour=(String)table1.getModel().getValueAt(row1,0);
								System.out.println("the desire hour is "+ hour);
								appCreation.setAppointmentHour(hour);
									
								appCreation.insertAppointmentToDB();
								
							  }	 
			    	       }//void mousePressed
  
			            });//MouseListner.

				   }//void run()
			};//Runnable()
					 
			Thread t1 = new Thread(lockingThread);

					 CalendarProgram cp=new CalendarProgram(docid, t1,appCreation);
						 
					}
				}catch(ArrayIndexOutOfBoundsException e){}
			}
		});

		
		
		//Presses in backward button and back to Operator Menu GUI//
		backward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new OperatorMenuGUI(patient);			
			}
		});
		forward.setVisible(false);
		setVisible(true);
		
	}
		
		




}