package com.motor.insurance.otherservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import com.motor.insurance.entity.Claim;
import com.motor.insurance.entity.PolicyHolder;
import com.motor.insurance.service.ClaimService;
import com.motor.insurance.service.ProposalService;

@Named
@ViewScoped
public class EmailSender {

	@Autowired
	ProposalService proposalService;
	
	@Autowired
	ClaimService claimService;
	
	@PostConstruct
	public void sendEmail() {
		List<PolicyHolder> pOwnerList = new ArrayList<PolicyHolder>();
		List<Claim> pClaimList = new ArrayList<Claim>();
		try {
			
			pOwnerList = proposalService.findAllProposaLOwnerActive();
			pClaimList=claimService.findAllClaimStatus();
			emailServiceForProposal(pOwnerList);
			emailServiceForClaim(pClaimList);
		}   catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void emailServiceForClaim(List<Claim> pClaimList) {
		 Session session = extractedAuthentication();
		 try {
			 System.out.println("Claim Mail service>>");
		       Message message = new MimeMessage(session);
	        	InternetAddress[] address = new InternetAddress[pClaimList.size()];
	  	        for (int j = 0; j < pClaimList.size(); j++) {
	      	    address[j] = new InternetAddress(pClaimList.get(j).getProposal().getPolicyHolder().getEmail());
				message.setFrom(new InternetAddress("yellowkiki120@gmail.com"));
	  	        }
				message.setRecipients(Message.RecipientType.TO, address);
	            System.out.println("email>>>>>>>>>"+address);
	            message.setHeader("Phonix Motor Insurance Company", "Announcing");
				message.setSubject("Accept for your  Claim  for Car coverage from Phoenix Motor Insurance ");
				message.setText("Dear "+"Customer" + "\n\n Your Claim for Car insurance is accepted!\n"
						+ "Please Contact to us +95942765171.\n"+"We will pay for your coverage!");
				Transport.send(message);

	  	       }
	        catch (MessagingException e) {
	         e.printStackTrace();
	     }
	}
	//email service for customer whose proposal is accepted by our company
	public void emailServiceForProposal(List<PolicyHolder>pOwnerList) {
		 Session session = extractedAuthentication();

	 try {
	       Message message = new MimeMessage(session);
        	InternetAddress[] address = new InternetAddress[pOwnerList.size()];
  	        for (int j = 0; j < pOwnerList.size(); j++) {
      	    address[j] = new InternetAddress(pOwnerList.get(j).getEmail());
			message.setFrom(new InternetAddress("yellowkiki120@gmail.com"));
  	        }
			message.setRecipients(Message.RecipientType.TO, address);
            System.out.println("email>>>>>>>>>"+address);
            message.setHeader("Phonix Motor Insurance Company", "Announcing");
			message.setSubject("Accept for your car proposal to insure from Phoenix Motor Insurance ");
			message.setText("Dear "+"Customer" + "\n\n Your proposal for car insurance is accepted!\n"
					+ "Would You like to start  pay for Policy of your car?\n"+"For more information please contact us!");
			Transport.send(message);

  	       }
        catch (MessagingException e) {
         e.printStackTrace();
     }
	 }

	private Session extractedAuthentication() {
		final String username = "yellowkiki120@gmail.com";
		final String password = "1998kiki";
		Properties prop = extracted();
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
			}
		});
		return session;
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
