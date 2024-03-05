package com.example.springmvc.controller.password;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.message.ErrorMessage;
import com.example.springmvc.model.ForgotPassword;
import com.example.springmvc.model.User;
import com.example.springmvc.service.ForgotPasswordService;
import com.example.springmvc.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletRequest;

@RestController
public class ForgotPasswordController {

	@Autowired
	private UserService userService;
	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@GetMapping("forgot-password")
	public ResponseEntity<?> postMethodName(@RequestBody FormForgotPassword email, ServletRequest request)
			throws UnsupportedEncodingException, MessagingException {
//		 TODO: process POST request
		ErrorMessage message = new ErrorMessage();
//		ForgotPassword forgotPassword = new ForgotPassword();
//		User user = userService.loadUserByEmail(email.getEmail());
//		if (user == null) {
//			return ResponseEntity.badRequest().body("Email not found");
//		}
//		forgotPassword.setToken(forgotPasswordService.generateToken());
//		forgotPassword.setExpireTime(forgotPasswordService.expireTime());
//		forgotPassword.setUser(user);
//		forgotPassword.setUsed(false);
//		forgotPasswordService.saveToken(forgotPassword);
//
//		String emailLink = "http://" + request.getServerName() + ":" + request.getServerPort()
//				+ "/admin/reset-password?token=" + forgotPasswordService.generateToken() + "?email=" + email.getEmail();
//
		message.setMessage(email.getEmail());
		message.setStatusCode(200);
//		forgotPasswordService.sendEmail(email.getEmail(), "Resetpassword", emailLink);
		return ResponseEntity.ok(message);
	}

	@PostMapping("forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody FormForgotPassword email, ServletRequest request) throws UnsupportedEncodingException, MessagingException {
		ErrorMessage message = new ErrorMessage();
		ForgotPassword forgotPassword = new ForgotPassword();
		Optional<User> user = userService.findUserbyEmail(email.getEmail());
		if (!user.isPresent()) {
			message.setMessage("Email not found");
			message.setStatusCode(400);
			return ResponseEntity.badRequest().body(message);
		}
		forgotPassword.setToken(forgotPasswordService.generateToken());
		forgotPassword.setExpireTime(forgotPasswordService.expireTime());
		forgotPassword.setUser(user.get());
		forgotPassword.setUsed(false);
		forgotPasswordService.saveToken(forgotPassword);

		String emailLink = "http://" + request.getServerName() + ":" + request.getServerPort()
				+ "/reset-password?token=" + forgotPassword.getToken() + "&email=" + email.getEmail();

		message.setMessage("check your email");
		message.setStatusCode(200);
		forgotPasswordService.sendEmail(email.getEmail(), "Reset password", emailLink);
		return ResponseEntity.ok(message);
	}

}
