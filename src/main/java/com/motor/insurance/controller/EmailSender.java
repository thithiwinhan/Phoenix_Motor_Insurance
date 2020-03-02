package com.motor.insurance.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.motor.insurance.entity.PolicyHolder;
import com.motor.insurance.service.ProposalService;

@ManagedBean
public class EmailSender {

	@Autowired
	ProposalService proposalService;
	
	@PostConstruct
	public void sendEmail() {
		List<PolicyHolder> pOwnerList = new ArrayList<PolicyHolder>();
		try {
			pOwnerList = proposalService.findAllProposaLOwnerActive();
			emailService(pOwnerList);

		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}
	public void emailService(List<PolicyHolder>pOwnerList) {
		final String username = "yellowkiki120@gmail.com";
	     final String password = "1998kiki";
	     Properties prop = extracted();
	     
	     Session session = Session.getInstance(prop,
	             new javax.mail.Authenticator() {
	                 protected PasswordAuthentication getPasswordAuthentication() {
	                 return new PasswordAuthentication(username, password);
	                 }
	             });

	     try {

		       Message message = new MimeMessage(session);
	   	  ArrayList <String>email= new ArrayList<String>();
	              
	                	InternetAddress[] address = new InternetAddress[pOwnerList.size()];
	          	        for (int j = 0; j < pOwnerList.size(); j++) {
		          	    address[j] = new InternetAddress(pOwnerList.get(j).getPhno());
						message.setFrom(new InternetAddress("yellowkiki120@gmail.com"));
						message.setRecipients(Message.RecipientType.TO, address);
						message.setSubject("Congradulation from Phoenix Motor Insurance ");
						message.setText("Dear "+pOwnerList.get(j).getName()+"" + "\n\n Your proposal for car insurance is accept!\n"
								+ "PLease start to pay to get your car policy");
						Transport.send(message);
	          	       }
	               		
					

	    
	       } catch (MessagingException e) {
	         e.printStackTrace();
	     }
	 }

		private Properties extracted() {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "25");
		prop.put(" mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return prop;
	}
	
}
