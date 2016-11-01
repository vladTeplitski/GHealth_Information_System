package boundry.utility;

import java.util.Random;

public class random_num {
	
	//random generator - generates a random number of 4 digits//
	
	/**
	 * random_number method generates a random number of 4 digits
	 * @return n,the random number
	 */
	public static int random_number(){
	Random rnd = new Random();
	
	int n = 10000 + rnd.nextInt(90000);
	
	
	return(n);
	}
	
	//end random generator//

}
