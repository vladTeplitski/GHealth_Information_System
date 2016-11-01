package boundry.utility;
//Import packages

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import entity.OperatorAppointmentCreation;
import boundry.GUI.OperatorAddAppointmentGUI;
import boundry.GUI.OperatorMenuGUI;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

 

public class CalendarProgram {

    static JLabel lblMonth, lblYear;
    static JButton btnPrev, btnNext;
    public static JTable tblCalendar;
    public static JComboBox cmbYear;
    static JFrame frmMain;
    static Container pane;
    public static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane stblCalendar; //The scrollpane
    public static JPanel pnlCalendar;
    public static int realYear, realMonth, realDay, currentYear, currentMonth;
    static JButton closeWindow;
    private MyJTable addappointments2;
    public  boolean stop=false;
    private OperatorAddAppointmentGUI w;
    private Thread t2; 
    public Runnable syncThread ;
    
    /**
     * CalendarProgram constructor for Calendar we create also frames,controls,border
     * @param docID is the id of the doctor
     * @param t1
     * @param appCreation its an object for creation appointment
     */
    //public static void main (String args[])
    public CalendarProgram(int docID, Thread t1,OperatorAppointmentCreation appCreation){
    
        //Look and feel

        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}

        

        //Prepare frame

        frmMain = new JFrame ("Queuing times"); //Create frame

        //frmMain.setSize(330, 375); //Set size to 400x400 pixels
        frmMain.setSize(400, 400);
        
        pane = frmMain.getContentPane(); //Get content pane

        pane.setLayout(null); //Apply null layout
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
        

        //Create controls
        lblMonth = new JLabel ("January");
        lblYear = new JLabel ("Change year:");
        cmbYear = new JComboBox();
        btnPrev = new JButton ("<<");
        btnNext = new JButton (">>");
        mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        pnlCalendar = new JPanel(null);
        closeWindow=new JButton("Close Window");
           //tblCalendar.setBackground(Color.blue);
      
        
        
        
        //Set border
        pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

         

        //Register action listeners

        btnPrev.addActionListener(new btnPrev_Action());

        btnNext.addActionListener(new btnNext_Action());

        cmbYear.addActionListener(new cmbYear_Action());
        //Add close button to Main Frame.
        frmMain.add(closeWindow);

        //Add controls to pane
     
        pane.add(pnlCalendar);
        //pane.add(closeWindow);

        pnlCalendar.add(lblMonth);

        pnlCalendar.add(lblYear);

        pnlCalendar.add(cmbYear);

        pnlCalendar.add(btnPrev);

        pnlCalendar.add(btnNext);

        pnlCalendar.add(stblCalendar);
        
         

        //Set bounds

        pnlCalendar.setBounds(0, 0, 320, 335);

        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);

        lblYear.setBounds(10, 305, 80, 20);

        cmbYear.setBounds(230, 305, 80, 20);

        btnPrev.setBounds(10, 25, 50, 25);

        btnNext.setBounds(260, 25, 50, 25);

        stblCalendar.setBounds(10, 50, 300, 250);
         closeWindow.setBounds(15, 330, 100, 35);
         

        //Make frame visible

        frmMain.setResizable(false);

        frmMain.setVisible(true);

         //Presses on close window-return to add appointmentGUI
        closeWindow.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
        		frmMain.setVisible(false);			
			}	
        });
        //Get real month/year

        GregorianCalendar cal = new GregorianCalendar(); //Create calendar

        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day

        realMonth = cal.get(GregorianCalendar.MONTH); //Get month

        realYear = cal.get(GregorianCalendar.YEAR); //Get year

        currentMonth = realMonth; //Match month and year

        currentYear = realYear;

         

        //Add headers

        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers

        for (int i=0; i<7; i++){

            mtblCalendar.addColumn(headers[i]);

        }

         

        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background

 

        //No resize/reorder

        tblCalendar.getTableHeader().setResizingAllowed(false);

        tblCalendar.getTableHeader().setReorderingAllowed(false);

 

        //Single cell selection

        tblCalendar.setColumnSelectionAllowed(true);

        tblCalendar.setRowSelectionAllowed(true);

        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

 

        //Set row/column count

        tblCalendar.setRowHeight(38);

        mtblCalendar.setColumnCount(7);

        mtblCalendar.setRowCount(6);

         

        //Populate table

        for (int i=realYear-100; i<=realYear+100; i++){

            cmbYear.addItem(String.valueOf(i));

        }

        //Refresh calendar
        refreshCalendar (realMonth, realYear); //Refresh calendar
        
        
        
        //Chosen the desire date//
        tblCalendar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {

				
				try{
					JTable table =(JTable) me.getSource();
					Point p = me.getPoint();
					int row = table.rowAtPoint(p);
					int colum=table.columnAtPoint(p);
					SimpleDateFormat displayDate = new SimpleDateFormat("yyyy-MM-dd");
					if (me.getClickCount() == 2){
						
					Date date=cal.getTime();
					
					int day=(int) table.getValueAt(row, colum);
					String str=(String)table.getColumnName(colum);
					System.out.println("the str is "+ str);
					int month=(currentMonth+1);
					
					String msg;
					if(month<10 && day<10)msg=currentYear+"-0"+month+"-0"+day;
					else if(month<10)msg=currentYear+"-0"+month+"-"+day;
					else if(day<10)msg=currentYear+"-0"+month+"-0"+day;
					else msg=currentYear+"-"+month+"-"+day;
					appCreation.setAppointmentDate(msg);
					
					//String msg1=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate());
					String msg1=displayDate.format(date);
					appCreation.setCreationDate(msg1);
					if(str.equals("Sat")) JOptionPane.showMessageDialog(null, "Hello,on Saturday not working,please chose another day ", "message", JOptionPane.WARNING_MESSAGE, null);
					else {
						//JOptionPane.showMessageDialog(null, "hello, The row is: "+row +" The colum is:"+colum +"The chosen day is: " + day+"The month is "+ month +"The year is "+currentYear+"The cuurent year:  "+ (date.getYear()+1900) +" The cuurent month: "+ (date.getMonth()+1)+"The current dat is "+date.getDate(), "message", JOptionPane.WARNING_MESSAGE, null);

				         t1.start();//Start the creation of hours table//
				         frmMain.setVisible(false);
					
					 }
					 
					 
					}
				}catch(ArrayIndexOutOfBoundsException e){}
			}
		});
        
    
   }

     

    public static void refreshCalendar(int month, int year){

        //Variables
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        int nod, som; //Number Of Days, Start Of Month

             

        //Allow/disallow buttons

        btnPrev.setEnabled(true);

        btnNext.setEnabled(true);

        if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early

        if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late

        lblMonth.setText(months[month]); //Refresh the month label (at the top)

        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar

        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

         

        //Clear table

        for (int i=0; i<6; i++){

            for (int j=0; j<7; j++){

                mtblCalendar.setValueAt(null, i, j);

            }

        }
         

        //Get first day of month and number of days

        GregorianCalendar cal = new GregorianCalendar(year, month, 1);

        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

         

        //Draw calendar

        for (int i=1; i<=nod; i++){

            int row = new Integer((i+som-2)/7);

            int column  =  (i+som-2)%7;

            mtblCalendar.setValueAt(i, row, column);

        }

 

        //Apply renderers

        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());

    }

 
/**
 *  getT2 
 * @return t2
 */
   public Thread getT2() {
		return t2;
	}


/**
 *   setT2
 * @param t2  that is equal to t2
 */
	public void setT2(Thread t2) {
		this.t2 = t2;
	}





static class tblCalendarRenderer extends DefaultTableCellRenderer{

        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){

            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            if (column == 0 || column == 6){ //Week-end

                setBackground(new Color(255, 220, 220));

            }

            else{ //Week

                setBackground(new Color(255, 255, 255));

            }

            if (value != null){

                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today

                    setBackground(new Color(220, 220, 255));

                }

            }

            setBorder(null);

            setForeground(Color.black);

            return this; 

        }

    }

 /**
  * 
  * this method calculate back one year 
  *
  */

    static class btnPrev_Action implements ActionListener{

        public void actionPerformed (ActionEvent e){

            if (currentMonth == 0){ //Back one year

                currentMonth = 11;

                currentYear -= 1;

            }

            else{ //Back one month

                currentMonth -= 1;

            }

            refreshCalendar(currentMonth, currentYear);

        }

    }

    /**
     * 
     * this method calculate Foward one year
     *
     */
    static class btnNext_Action implements ActionListener{

        public void actionPerformed (ActionEvent e){

            if (currentMonth == 11){ //Foward one year

                currentMonth = 0;

                currentYear += 1;

            }

            else{ //Foward one month

                currentMonth += 1;

            }

            refreshCalendar(currentMonth, currentYear);

        }

    }
/**
 * 
 * in this method we have the ActionListener and choose the Selected Item
 *
 */
    static class cmbYear_Action implements ActionListener{

        public void actionPerformed (ActionEvent e){

            if (cmbYear.getSelectedItem() != null){

                String b = cmbYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }

    //Update desire date  in DB.
//    public void UpdateDesireDate(int day,int month,int year){
//    	
//    	
//    	
//    	
//    	
//    	
//    	String message1 = "new INSERT INTO abstract_referral VALUES(?, '2016-05-27', 1, 1111, 201345789)";
//    	
//    	
//    }
    
    
    
    
    
//public boolean CheckDateChosenAfterSixMonth(int day,int month,int currentYear){
//	
//}





}
