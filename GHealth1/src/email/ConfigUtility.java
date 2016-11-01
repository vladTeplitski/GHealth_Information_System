package email;

import java.util.Properties;

public class ConfigUtility {

	public static Properties configProps = loadProperties();

	/**
	 * 
	 * @return Properties
	 */
	private static Properties loadProperties() {
		Properties defaultProps = new Properties();
		// sets default properties
		defaultProps.setProperty("mail.smtp.host", "smtp.gmail.com");
		defaultProps.setProperty("mail.smtp.port", "587");
		defaultProps.setProperty("mail.user", "ghealthbraude@gmail.com");
		defaultProps.setProperty("mail.password", "Braude555");
		defaultProps.setProperty("mail.smtp.starttls.enable", "true");
		defaultProps.setProperty("mail.smtp.auth", "true");
		defaultProps.setProperty("mail.sign", "Best regards,\nIHealth");
		
		Properties configProps = new Properties(defaultProps);
		
		return configProps;
	}

}