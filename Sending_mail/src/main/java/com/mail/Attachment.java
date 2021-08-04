package com.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Attachment {

	 public static void main( String[] args )
	    {
	    	String message = "Hello, Dear...This is mail for security check.Hope you are doing well during this pandemic."
	    			+ "We are offering you the winter courses in just Rs.199/- only.."
	    			+ "HURRY UP !!";
	    	String subject="JavaBrains : confirmation mail.";
	    	String to="reciever gmail account here";
	    	String from="sender gmail account here";
	    	
	    	
	    	sendAttach(message,subject,to,from);
	    
	    }

	 // this method is responsible for sending message with attachment.....
	private static void sendAttach(String message, String subject, String to, String from) {
		
		// Variable for gmail
				String host = "smtp.gmail.com";
				
				// get the system property
				Properties properties = System.getProperties();
				System.out.println("PROPERTIES "+properties);
				
				//setting important to properties object
				
				//host set
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.port", "465");
				properties.put("mail.smtp.ssl.enable", "true");
				properties.put("mail.smtp.auth", "true");
				
				//STEP 1: To get the session object..
				Session session = Session.getInstance(properties, new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("sender gmail account here", "enter ur gamil password here..");
					}
					
				});
				session.setDebug(true);
				
				//STEP 2: Compose the message..
				
				MimeMessage mimeMsg = new MimeMessage(session);
				
				try {
					
					// from email...
					mimeMsg.setFrom(from);
					
					//adding recipient to message...
					mimeMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					
					//adding subject to message...
					mimeMsg.setSubject(subject);
					
					//attachment...
					String path = "C:\\Users\\yudiv\\Desktop\\pic.jpg";
					
					MimeMultipart mimeMultipart = new MimeMultipart();
					
					//to set text in mimeMultipart.....
					 MimeBodyPart textMime = new MimeBodyPart();
					 
					 //to set file in mimeMultipart....
					 MimeBodyPart fileMime = new MimeBodyPart();
					
					 try {
						 textMime.setText(message);
						 
						 File file = new File(path);
						 fileMime.attachFile(file);
						 
						 mimeMultipart.addBodyPart(textMime);
						 mimeMultipart.addBodyPart(fileMime);
						
					} catch (Exception e) {
						e.printStackTrace();
						}
					 
					 mimeMsg.setContent(mimeMultipart);
					 
					// send message....!!!
					
					//STEP 3: send message using Transport class...
					Transport.send(mimeMsg);
					
					System.out.println("Attachment sent success..............");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

}
