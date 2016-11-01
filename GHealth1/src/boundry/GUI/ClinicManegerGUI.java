package boundry.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import ocsf.Main.MainClient;
import boundry.utility.MyJTable;
import entity.User;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextArea;

public class ClinicManegerGUI extends AbstractMainGUI {

	private static final long serialVersionUID = 1L;
	private JButton monthReport;
	private User maneger;
	public MyJTable addappointments;
	final static SimpleDateFormat displayDate = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * ClinicManegerGUI constructor its the week report
	 * its calculates the avg ,min and max,Standard deviation 
	 * @param clinicID -due to it calculates the min ,max ,avg and the Standard deviation,to all the clinics that are exist calculaes them
	 */
	public ClinicManegerGUI(int clinicID) {
		long diff=0;
		long avg=0;
		long max=0,min=0;
		double statisticsdeviation=0;
		Date date=new Date();
		DecimalFormat df = new DecimalFormat("#.##");
		
		String message="select CreationDate,appointmentDate from operator_appointment_creation where ClinicID_OAC="+clinicID+" and date_sub('"+displayDate.format(date)+"',INTERVAL 7 DAY)<=appointmentDate";
	
	     addappointments=MyJTable.newTable(message, 600, 100, 400, 400);
	    getContentPane().add(addappointments.getJps());
	    
	    ArrayList<Date> d1 = new ArrayList<Date>();
	    ArrayList<Date> d2 = new ArrayList<Date>();
	    ArrayList<Long> d3 = new ArrayList<Long>();
	    
	    
	    CachedRowSet row;
		try {
			row = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(message);
			while(row.next()){
				d1.add(displayDate.parse(row.getString(1)));
				d2.add(displayDate.parse(row.getString(2)));
				
				System.out.println("d1. "+displayDate.parse(row.getString(1)));
				System.out.println("d2. "+displayDate.parse(row.getString(2)));
				
			}
		min=Math.abs(d1.get(0).getTime()-d2.get(0).getTime());
		for(int i=0;i<d1.size();i++){
			diff=diff+(Math.abs(d1.get(i).getTime()-d2.get(i).getTime()));
			d3.add((Math.abs(d1.get(i).getTime()-d2.get(i).getTime()))/(24*60*60*1000));
			if(Math.abs(d1.get(i).getTime()-d2.get(i).getTime())>max) max=(Math.abs(d1.get(i).getTime()-d2.get(i).getTime()));//Search the max value.
			if(min>Math.abs(d1.get(i).getTime()-d2.get(i).getTime()))min=Math.abs(d1.get(i).getTime()-d2.get(i).getTime());//Search the minimum value.
			}
		
		diff=diff/(24*60*60*1000);  //days
		max=max/(24*60*60*1000);//max value in days
		min=min/(24*60*60*1000);//minimum value in days
		
		
		try{
		avg=(int)diff/(long)row.size();
		for(int j=0;j<d3.size();j++)
		{
			statisticsdeviation=statisticsdeviation+Math.pow((d3.get(j)-avg),2);
		}
		
		statisticsdeviation=statisticsdeviation/d3.size();
		statisticsdeviation=Math.sqrt(statisticsdeviation);
		String dx=df.format(statisticsdeviation);
		statisticsdeviation=Double.valueOf(dx);
		
		
		}
		catch (ArithmeticException e)  // catch exception if no info = devide by 0
		{
			JOptionPane.showMessageDialog(null, "No information for calculation.");
		}
		System.out.println("avg= "+diff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	    JLabel lblNewLabel = new JLabel("Weekly Report for clinic number:"+clinicID);
	    lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
	    lblNewLabel.setForeground(Color.YELLOW);
	    lblNewLabel.setBounds(600, 60, 400, 50);
	    getContentPane().add(lblNewLabel);
	    
	    JTextArea txter = new JTextArea();
	    txter.setBackground(Color.BLUE);
	    txter.setText("Avarage time waiting for appointment:"+avg+"\nNumber of treated patients: "+d1.size()+"\nMax value: "+max+"\nMinimium value: "+min+"\nStatistics Deviation value: "+statisticsdeviation);
	    txter.setForeground(Color.WHITE);
	    txter.setBounds(183, 192, 362, 80);
	    
	    
	    getContentPane().add(txter);
	    
	    JLabel lblNewLabel_1 = new JLabel("Statistic calculations");
	    lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    lblNewLabel_1.setForeground(Color.YELLOW);
	    lblNewLabel_1.setBounds(183, 154, 200, 50);
	    getContentPane().add(lblNewLabel_1);

	    
	    
	    searchText.setVisible(false);
		forward.setVisible(false);
	
		setVisible(true);
		
	}
}