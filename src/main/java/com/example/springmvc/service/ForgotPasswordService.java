package com.example.springmvc.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.springmvc.exceptionhandler.DataNotFoundException;
import com.example.springmvc.model.ForgotPassword;
import com.example.springmvc.repository.ForgotPasswordRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ForgotPasswordService {

	@Autowired
	private ForgotPasswordRepository forgotPasswordRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	private final int MINUTES = 5;

	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	public LocalDateTime expireTime() {
		return LocalDateTime.now().plusMinutes(MINUTES);
	}

	public void sendEmail(String to, String subject, String emaiLink)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		String mailContent = "Click the link <br>" + " <p><a href=\"" + emaiLink + "\">Chang my password</a></p>";

		helper.setText(mailContent, true);
		helper.setFrom("bahuan200102@gmail.com", "admin support");
		helper.setSubject(subject);
		helper.setTo(to);
		System.out.println(mailContent);
		javaMailSender.send(message);
	}

	public Optional<ForgotPassword> getToken(String token) {

		return forgotPasswordRepository.findByToken(token);
	}

	public void saveToken(ForgotPassword forgotPassword) {
		forgotPasswordRepository.save(forgotPassword);
	}
}
