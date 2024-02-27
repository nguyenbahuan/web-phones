package com.example.springmvc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.config.CustomUserDetails;
import com.example.springmvc.dto.ReqRes;
import com.example.springmvc.model.User;
import com.example.springmvc.payload.LoginRequest;
import com.example.springmvc.payload.LoginResponse;
import com.example.springmvc.payload.RandomStuff;
import com.example.springmvc.service.AuthService;
import com.example.springmvc.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1")
public class LoginController {

	@Autowired
	private AuthService authService;

//	@Autowired
//	private JwtTokenProvider tokenProvider;

	@PostMapping(path = "login")
	public ResponseEntity<ReqRes> login(@Valid @RequestBody ReqRes loginRequest) {
		return ResponseEntity.ok(authService.signIn(loginRequest));
	}

	@PostMapping(path = "register")
	public ResponseEntity<ReqRes> register(@Valid @RequestBody ReqRes signupRequest) {
		return ResponseEntity.ok(authService.signUp(signupRequest));
	}

	@GetMapping("login")
	public RandomStuff login() {
		return new RandomStuff("ok");
	}

	@GetMapping("random")
	public RandomStuff randomStuff() {
		return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
	}

	@PostMapping("/refresh")
	public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest) {
		return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
	}
//	@PostMapping()
//	public void handleLogin(@RequestBody String email, String password) {
////		Optional<User> user = service.n
//	}
}
