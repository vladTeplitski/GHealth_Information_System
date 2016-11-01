package boundry.utility;

import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;






import ocsf.Main.MainClient;

public class MyJTable extends JTable implements UtilltyGUI {

	private static final long serialVersionUID = 1L;
	private JScrollPane jps;
/**
 * newTable 
 * @param message
 * @param x
 * @param y
 * @param width of the table
 * @param  height of the table
 * @return dataList
 */
	public static MyJTable newTable(String message, int x, int y, int width, int height) {
		Object[] columns = null;
		ArrayList<Object[]> dataList = null;
		try {

			CachedRowSet data = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(message);
			dataList = new ArrayList<Object[]>();
			ResultSetMetaData rsmd = data.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			columns = new String[columnsNumber];
			for (int i = 1; i <= columnsNumber; i++)
				columns[i-1] = rsmd.getColumnName(i);
			
			while (data.next()){
				Object[] temp = new Object[columnsNumber];
				for (int i = 1; i <= columnsNumber; i++){
					temp[i-1] = data.getObject(i);
				}
				dataList.add(temp);		    
			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new MyJTable(dataList.toArray(new Object[dataList.size()][]), columns, x, y , width, height);
	}

	/**
	 * newTable1 creating a table with hours
	 * @param message  message that we write
	 * @param x
	 * @param y
	 * @param width of a table
	 * @param height of a table
	 * @return columns2
	 */
	public static MyJTable newTable1(String message, int x, int y, int width, int height) {
		Object[] columns = null;
		Object[] columns1 = null;
		ArrayList<String[]> columns2 = new ArrayList<String[]>();
		columns2.add(new String[]{"08:00"});columns2.add(new String[]{"08:30"});columns2.add(new String[]{"09:00"});columns2.add(new String[]{"09:30"});
		columns2.add(new String[]{"10:00"});columns2.add(new String[]{"10:30"});columns2.add(new String[]{"11:00"});columns2.add(new String[]{"11:30"});
		columns2.add(new String[]{"12:00"});columns2.add(new String[]{"12:30"});columns2.add(new String[]{"13:00"});columns2.add(new String[]{"13:30"});
		columns2.add(new String[]{"14:00"});columns2.add(new String[]{"14:30"});columns2.add(new String[]{"15:00"});columns2.add(new String[]{"15:30"});
		columns2.add(new String[]{"16:00"});columns2.add(new String[]{"16:30"});columns2.add(new String[]{"17:00"});
		
		
		ArrayList<Object[]> dataList = null;
		try {

			System.out.println("the message is:"+message);
			CachedRowSet data = (CachedRowSet)MainClient.mainClient.sendAndWaitForReply(message);
			dataList = new ArrayList<Object[]>();
			ResultSetMetaData rsmd = data.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			columns = new String[columnsNumber];
			columns[0] = rsmd.getColumnName(1);

			while (data.next()){	
				Object[] temp = new Object[columnsNumber];
				//Delete the occupancy hours from the hours list// 
				for(int j=0;j<columns2.size();j++){
					if((data.getString(1)).equals(columns2.get(j)[0])){
						System.out.println(". data"+ data.getString(1) );
						columns2.remove(j);
					}			
				}
			}

			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new MyJTable(columns2.toArray(new Object[columns2.size()][]), columns, x, y , width, height);
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @param data 
	 * @param columns of the table
	 * @param x
	 * @param y
	 * @param width of the table
	 * @param height of the table
	 */
	
	
	
	public MyJTable(Object[][] data, Object[] columns, int x, int y, int width, int height) {
		super(new DefaultTableModel(data, columns));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment( SwingConstants.CENTER );
		setRowHeight(getRowHeight()+5);
		setFillsViewportHeight(true);
		jps = new JScrollPane(this);
		jps.setBounds(x,y,width,height);
		getTableHeader().setFont(UtilltyGUI.tableHeader);
		setFont(smallText);
		for (int j = 0; j < columns.length; j++)
			getColumnModel().getColumn(j).setCellRenderer(r);
		
	}
	
	
	/**
	 *  isCellEditable method is that we can edit the cells of the table
	 *  @param data that in the table
	 *  @param columns of a table
	 *  @return boolean
	 */


	public boolean isCellEditable(int data, int columns){
		return false;
	}

	/**
	 * 
	 * @return jps
	 */
	public JScrollPane getJps() {
		return jps;
	}

/**
 * Component prepareRenderer
 * @param r is an object from TableCellRenderer
 * @param data 
 * @param columns of a table
 * @return c is a component that if we get that data is even or not we return c according to the data 
 */
	public Component prepareRenderer(TableCellRenderer r, int data, int columns){
		Component c = super.prepareRenderer(r, data, columns);

		if (data % 2 == 0)
			c.setBackground(Color.WHITE);

		else
			c.setBackground(lightGrey);

		if(isCellSelected(data, columns)){
			c.setBackground(selectColor);
		}

		return c;
	}

	/**
	 * rePopulate method delete all the rows from table &populate the row by message
	 * @param message the populate of the row is by message
	 */
	public void rePopulate(String message){
		DefaultTableModel model = (DefaultTableModel) getModel();
		model.setRowCount(0);
		try {
			CachedRowSet data = MainClient.mainClient.sendAndWaitForReply(message);
			ResultSetMetaData rsmd = data.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (data.next()){
				Object[] temp = new Object[columnsNumber];
				for (int i = 1; i <= columnsNumber; i++)
					temp[i-1] = data.getObject(i);
				model.addRow(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

