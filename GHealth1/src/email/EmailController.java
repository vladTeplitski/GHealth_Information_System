package email;

import java.util.Date;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailController {

	/**
	 * sendEmail method sending email 
	 * @param toAddress -where we want to send the mail
	 * @param subject -the subject of the email
	 * @param message ,the message of mail
	 * @throws MessagingException 
	 * @return PasswordAuthentication
	 */
	public static void sendEmail(String toAddress, String subject, String message) throws MessagingException {
		final String userName = ConfigUtility.configProps.getProperty("mail.user");
		final String password = ConfigUtility.configProps.getProperty("mail.password");
		final String sign = ConfigUtility.configProps.getProperty("mail.sign");
		
		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(ConfigUtility.configProps, auth);

		// creates a new e-mail message
		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject, "utf-8");
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("<div dir=\"ltl\" align=\"left\">" +
						escape(message)+ "<br><br>-------------------------------<br>" + escape(sign) + "</div>", "text/html; charset=utf-8");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

//		// adds attachments
//		if (attachFiles != null && attachFiles.length > 0) {
//			for (File aFile : attachFiles) {
//				MimeBodyPart attachPart = new MimeBodyPart();
//
//				try {
//					File dest = new File("Document" + '.' + FilenameUtils.getExtension(aFile.getName()));
//					FileUtils.copyFile(aFile, dest);
//					messageBodyPart.setContent("<div dir=\"rtl\" align=\"right\">" + escape(message) + "<br><br><br><b>оцешу чебх: "+ aFile.getName() + "</b>"
//							+ "<br>-------------------------------<br>" + escape(sign) + "</div>", "text/html; charset=utf-8");
//					attachPart.attachFile(dest);
//					multipart.addBodyPart(attachPart);
//					dest.deleteOnExit();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
//		
		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}
	/**
	 * escape method translate escape codes in string to html escape codes
	 * @param s for input string
	 * @return
	 */
	
	public static String escape(String s) {
	    StringBuilder builder = new StringBuilder();
	    boolean previousWasASpace = false;
	    for( char c : s.toCharArray() ) {
	        if( c == ' ' ) {
	            if( previousWasASpace ) {
	                builder.append("&nbsp;");
	                previousWasASpace = false;
	                continue;
	            }
	            previousWasASpace = true;
	        } else {
	            previousWasASpace = false;
	        }
	        switch(c) {
	            case '<': builder.append("&lt;"); break;
	            case '>': builder.append("&gt;"); break;
	            case '&': builder.append("&amp;"); break;
	            case '"': builder.append("&quot;"); break;
	            case '\n': builder.append("<br>"); break;
	            // We need Tab support here, because we print StackTraces as HTML
	            case '\t': builder.append("&nbsp; &nbsp; &nbsp;"); break;  
	            default:
	                if( c < 128 ) {
	                    builder.append(c);
	                } else {
	                    builder.append("&#").append((int)c).append(";");
	                }    
	        }
	    }
	    return builder.toString();
	}
	

}