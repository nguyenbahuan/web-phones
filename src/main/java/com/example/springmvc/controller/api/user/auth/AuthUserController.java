package com.example.springmvc.controller.api.user.auth;

import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.jwt.JwtUser;
import com.example.springmvc.message.ErrorMessage;
import com.example.springmvc.service.LoginService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthUserController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("login")
	public ResponseEntity<JwtUser> login(@Valid @RequestBody FormUserLogin loginRequest, BindingResult bindingResult) {
		//TODO: process POST request
		
		return ResponseEntity.ok(loginService.signInUser(loginRequest, bindingResult));
	}
	@PostMapping("register")
	public ResponseEntity<ErrorMessage> register(@Valid @RequestBody FormUserRegister request, BindingResult bindingResult) {
		//TODO: process POST request
		
		return ResponseEntity.ok(loginService.registerUser(request, bindingResult));
	}
	
	
	
}
