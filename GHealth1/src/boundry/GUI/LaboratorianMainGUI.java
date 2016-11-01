package boundry.GUI;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import control.LaboratorianController;
import entity.TestReferral;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ImageIcon;

/**
 * 
 * @author vlad teplitski
 *
 */

public class LaboratorianMainGUI extends AbstractMainGUI {
	
	/*static var  */
	private static final long serialVersionUID = 314531852940284335L;
	public int flag=2;

/**
 * constructor without parameters
 *checking if referral is in database
 */
	public LaboratorianMainGUI() {
	
	searchText.setBounds(890, 290, 229, 36);
	searchText.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		setTitle("laboritorian");
		searchLabel.setText("Refferal ID");
		
		JLabel lblHelloLaboratorian = new JLabel("Laboratorian system");
		lblHelloLaboratorian.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblHelloLaboratorian.setForeground(Color.YELLOW);
		lblHelloLaboratorian.setBounds(720, 238, 245, 36);
		getContentPane().add(lblHelloLaboratorian);
		
		forward.addActionListener(new ActionListener()
		{		

			public void actionPerformed(ActionEvent e) 
			{
				int ref;
				try 
				{
					
		
					if(LaboratorianController.checkReferral(searchText.getText()))
						{
						    ref=Integer.parseInt(searchText.getText());
							TestReferral test_ref=new TestReferral(ref);
							new LaboratorianMenuGUI(test_ref);
							setVisible(false); //close the window
						}
					else
						{
							JOptionPane.showMessageDialog(null, "The referral doesn't exist or wrong referral number.");
						}
				} 
				catch (java.lang.NumberFormatException e1) //string exception
				{
						JOptionPane.showMessageDialog(null, "Please fill the field with numbers.");
				} 

				catch (Exception e1) {
					e1.printStackTrace();
				}

				

			}
		});
		
		setVisible(true);

		
	}
}

