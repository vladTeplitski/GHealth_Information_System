package boundry.utility;

import java.awt.Toolkit;

public class MyResolution {
	private final static int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final static int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	/**
	 * getFrameWidth
	 * @return width
	 */
	public static int getFrameWidth(){
		return width;
	}
	
	/**
	 * getFrameHieght for resolution 
	 * @return height
	 */
	public static int getFrameHieght(){
		//resolution 1920x1080 for Vlad or Yafit
		if(width == 1920 && height == 1080)
			return height - 100;
		//resolution 1680x1050 for computer lab
		if(width == 1680 && height == 1050)
			return height - 120;
		//resolution 1366x768 our default
		else
			return height - 60;
	}
}
