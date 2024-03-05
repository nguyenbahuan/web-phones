package com.example.springmvc.controller.api.admin.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.controller.api.user.auth.FormUserLogin;
import com.example.springmvc.jwt.JwtUser;
import com.example.springmvc.message.ErrorMessage;
import com.example.springmvc.service.AuthService;
import com.example.springmvc.service.LoginService;
import com.example.springmvc.util.JsonResult;

import jakarta.validation.Valid;

@RestController
public class AuthAdminController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/admin/login")
	public ResponseEntity<JwtUser> login(@Valid @RequestBody FormLogin requets, BindingResult bindingResult)
			throws Exception {

		return ResponseEntity.ok(loginService.signInAdmin(requets, bindingResult));
	}
	
	@PostMapping("/admin/register")
	public ResponseEntity<ErrorMessage> signup(@Valid @RequestBody FormRegister request, BindingResult bindingResult) {
		//TODO: process POST request
		
		return ResponseEntity.ok(loginService.register(request, bindingResult));
	}
	

}
