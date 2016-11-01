package boundry.utility;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

public interface UtilltyGUI {

	final static Color frameColor = new Color(0,0,102);
	final static Color panelColor = new Color(204,204,255);
	static final Color lightGrey = new Color(224,224,224);
	static final Color selectColor = new Color(204,255,153);
	final static Font bigText = new Font("Times New Roman", Font.BOLD, 32);
	final static Font smallText = new Font("Times New Roman", Font.PLAIN, 20);
	final static Font tableHeader = new Font("Times New Roman", Font.BOLD, 20);
	final static SimpleDateFormat displayDate = new SimpleDateFormat("yyyy-MM-dd");
	final static SimpleDateFormat displayHour = new SimpleDateFormat("HH:mm");
	final static String[] doctors = {"dentist","kids","Orthopedist","women","Otolaryngology","eyes"};

	
	final static int frameWidth = MyResolution.getFrameWidth();
	final static int frameHeight = MyResolution.getFrameHieght();
}