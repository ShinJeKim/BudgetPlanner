package com.apps.user.domain;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.apps.user.controller.UserController;
import com.sun.activation.registries.MailcapParseException;
	@Component
	public class EmailSender  {
	     
		
		private Logger log = LoggerFactory.getLogger(EmailSender.class);
		
		
	    @Autowired
	    protected MailSender  mailSender;
	 
	    public void SendEmail(EmailVO email) throws Exception {
	    	
	    	log.debug("=====================sendEmail===================");
	    	log.debug("=mailSender="+mailSender.toString());
	    	log.debug(email.toString());
	    	
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        String sender = email.getReceiver();
	        
	        log.debug("sender : "+sender);
	        
	        mailMessage.setTo(email.getReceiver());
			mailMessage.setFrom("thirdp03@gmail.com");
			mailMessage.setSubject(email.getSubject());
			mailMessage.setText(email.getContent());
           
			log.debug("mailMessage(TO) : "+mailMessage.getTo());
			log.debug("mailMessage(From) : "+mailMessage.getFrom());
			log.debug("mailMessage(Subject) : "+mailMessage.getSubject());
			log.debug("mailMessage(Text) : "+mailMessage.getText());
			
			mailSender.send(mailMessage);
	        
	        
	    }

}
