package com.techelevator.model;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Message;


public class Email {

	public static void sendEmail (String recepient, String htmlString) throws Exception {
		System.out.println("Preparing to send Email");
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String myAccountEmail = "chatbottestingemail@gmail.com";
		String password = "TechElevator10";
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		Message msg = prepareMessage(session, myAccountEmail, recepient, htmlString);
		
		Transport.send(msg);
		System.out.println("Message sent successfully");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String htmlString) {
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(myAccountEmail));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			msg.setSubject("Your Chatbot Transcript");
			String htmlCode = htmlString;
			msg.setContent(htmlCode, "text/html");
			//first string for html content,  second for mindtype 
			return msg;
		} catch (Exception ex) {
			Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}

