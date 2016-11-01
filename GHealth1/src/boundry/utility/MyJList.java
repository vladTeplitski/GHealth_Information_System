package boundry.utility;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

public class MyJList extends JList<String> {

	private static final long serialVersionUID = 1L;
	private JScrollPane jps;

	public MyJList(String[] array, int x, int y, int width, int height){
		super(array);
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
	    setBorder(lineBorder);
	    setFont(UtilltyGUI.smallText);
	    setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    setSelectionModel(new DefaultListSelectionModel() {
			private static final long serialVersionUID = 1L;
			@Override
	        public void setSelectionInterval(int index0, int index1) {
	            if(super.isSelectedIndex(index0)) {
	                super.removeSelectionInterval(index0, index1);
	            }
	            else {
	                super.addSelectionInterval(index0, index1);
	            }
	        }
	    });
	    jps = new JScrollPane(this);
	    jps.setBounds(x, y, width, height);
	}

	public JScrollPane getJps() {
		return jps;
	}
}
