package ie.dit.dt354spring.components;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class MailComponent {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendRegMail(String to, String bodyT, String bodyH) throws MessagingException{
		String subject = "Welcome to DT354 Website";
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(bodyT, bodyH);
		
		mailSender.send(message);
	}
	
	public void sendEnoteMail(String to, String bodyT, String bodyH) throws MessagingException{
		String subject = "New Enote";
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(bodyT, bodyH);
		
		mailSender.send(message);
	}
	
	public void sendPassMail(String to, String bodyT, String bodyH) throws MessagingException{
		String subject = "Password Change";
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(bodyT, bodyH);
		
		mailSender.send(message);
	}
}
