package com.example.springmvc.controller.password;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvc.model.ForgotPassword;
import com.example.springmvc.model.User;
import com.example.springmvc.service.ForgotPasswordService;
import com.example.springmvc.service.UserService;

@Controller()
public class ResetPasswordController {

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserService userService;
	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@GetMapping("reset-password")
	public String getMethodName(@RequestParam(name = "token") String token,
			@RequestParam(name = "email") String email, Model model) {
		Optional<ForgotPassword> forgotPassword = forgotPasswordService.getToken(token);
		if (!forgotPassword.isPresent() || forgotPassword.get().isUsed()) {
			model.addAttribute("message", "token không hợp lệ");
			return "message-password";
		} else if (ChronoUnit.MINUTES.between(forgotPassword.get().getExpireTime(), LocalDateTime.now()) > 5) {
			model.addAttribute("message", "token hết hạn");
			return "message-password";
		}
		if (!forgotPassword.get().getUser().getEmail().equals(email)) {
			model.addAttribute("message","Emai không đúng");
			return "message-password";
			
		}
		return "reset-password";
	}

	@PostMapping("reset-password")
	public String handlerResetpassword(FormResetPassword password, @RequestParam(name = "email") String email,
			@RequestParam(name = "token") String token, Model model) {
		Optional<User> user = userService.findUserbyEmail(email);
		Optional<ForgotPassword> passwordToken = forgotPasswordService.getToken(token);
		if (user.isPresent() && passwordToken.isPresent()) {

			user.get().setPassword(encoder.encode(password.getPassword()));
			userService.saveUser(user.get());
			passwordToken.get().setUsed(true);
			forgotPasswordService.saveToken(passwordToken.get());
			model.addAttribute("message", "Thay đổi mật khẩu thành công");
			return "message-password";
		}
		model.addAttribute("message", "Thay đổi mật khẩu thất bại");
		return "message-password";

	}

}
