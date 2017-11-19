package com.g4life.service.confirmaccount;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;


public class SendToEmail {

	public void sendCodeToEmail(String email, String code){
	    Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", true); // added this line
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", "username");
	    props.put("mail.smtp.password", "password");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", true);

	    Session session = Session.getInstance(props,new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return  new PasswordAuthentication("tranhuyduan", "23071993");
	        }
	    });
//	    session.setDebug(true);

	    MimeMessage message = new MimeMessage(session);

	    System.out.println("Port: "+session.getProperty("mail.smtp.port"));

	    // Create the email addresses involved
	    try {
	        InternetAddress from = new InternetAddress("tranhuyduan");
	        message.setSubject("Confirm G4LIFE account");
	        message.setFrom(from);
	        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

	        // Create a multi-part to combine the parts
	        Multipart multipart = new MimeMultipart("alternative");

	        // Create your text message part
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(code);

	        // Add the text part to the multipart
	        multipart.addBodyPart(messageBodyPart);

	        // Create the html part
	        messageBodyPart = new MimeBodyPart();
	        String htmlMessage = "Our html text";
	        messageBodyPart.setContent(code, "text/html");


	        // Add html part to multi part
	        multipart.addBodyPart(messageBodyPart);

	        // Associate multi-part with message
	        message.setContent(multipart);

	        // Send message
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", "tranhuyduan", "23071993");
	        System.out.println("Transport: "+transport.toString());
	        transport.sendMessage(message, message.getAllRecipients());


	    } catch (AddressException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (MessagingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
}
