package com.motor.insurance;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class MyApp {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
	        extracted();
	    }
	private static void extracted() {
		String password = "12345";
		String algorithm = "SHA";

		byte[] plainText = password.getBytes();

		try {
		    MessageDigest md = MessageDigest.getInstance(algorithm);

		    md.reset();
		    md.update(plainText);
		    byte[] encodedPassword = md.digest();

		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i < encodedPassword.length; i++) {
		        if ((encodedPassword[i] & 0xff) < 0x10) {
		            sb.append("0");
		        }

		        sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
		    }

		    System.out.println("Plain    : " + password);
		    System.out.println("Encrypted: " + sb.toString());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
		  
	/*
	 * Calendar cal = Calendar.getInstance(); Date today = new Date();
	 * System.out.println("today"+today); cal.setTime(today); cal.add(Calendar.YEAR,
	 * 1);
	 * 
	 * // MessageDigest.getInstance("MD5");
	 * System.out.println(MessageDigest.getInstance("MD5"));
	 * 
	 * System.out.println("date"+cal.getTime()); }
	 */
}
