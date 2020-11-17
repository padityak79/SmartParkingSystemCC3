/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingsystem;

import java.util.Properties;
import java.util.Date;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	public static void sendEmail(int type, parkingSlot recipentSlot) throws Exception {
		// TODO Auto-generated method stub

		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String account = "IIT2019165@iiita.ac.in";
		String password = "incorrect3909";
		String recipent = recipentSlot.getEmailId();
                
		Session session = Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account, password);
				
			}
			
		
		});
		
		
		Message message = prepareMessage(type, session, account, recipent, recipentSlot) ;
				
		Transport.send(message);
		
	}

	private static Message prepareMessage(int type, Session session, String account, String recipent, parkingSlot recipentSlot) {
		// TODO Auto-generated method stub
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(account));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
			message.setSubject("CC3 Smart Parking System Notification.");
                        
                        String htmlMessage = "";
                        if(type == 0) {
                            htmlMessage = "<H2> Parking Successfull</H2>"
                                    + "Your Parking Id :" + recipentSlot.getParkingId() + "<br>"
                                    + "Parking Expiry Date : " + recipentSlot.getExpiryDate() + "<br>";
                        } 
                        
                        if(type == 1) {
                            htmlMessage = "<H2> Parking Renewal Successfull</H2>"
                                    + "Your Parking Id :" + recipentSlot.getParkingId() + "<br>"
                                    + "Parking Expiry Date : " + recipentSlot.getExpiryDate() + "<br>";
                        }
                        
                        if(type == 2) {
                            htmlMessage = "<H2> Parking Expired</H2>"
                                    + "Dear Customer, your parking services have expired on " + recipentSlot.getExpiryDate() + "<br>"
                                    + "Kindly extend your services or unregister youreself."
                                    + "If already extended or un-registered please ignore";
                        }
                        
                        if(type == 3) {
                            htmlMessage = "<H2> Parking Un-Registration Successfull</H2>"
                                    + "Dear Customer, you have successfully un-registered yourself";
                        }
			
			message.setContent(htmlMessage , "text/html");
			return message;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}


/**
 *
 * @author user
 */
 

