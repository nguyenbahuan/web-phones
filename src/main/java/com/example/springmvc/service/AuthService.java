package com.example.springmvc.service;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.springmvc.dto.ReqRes;
import com.example.springmvc.jwt.JWTUtils;
import com.example.springmvc.model.Login;
import com.example.springmvc.model.User;
import com.example.springmvc.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public ReqRes signUp(ReqRes registrationRequest, BindingResult bindingResult) {
		ReqRes resp = new ReqRes();
		Optional<User> optionalUser = userRepository.findUserByEmail(registrationRequest.getEmail());
		try {
			if (bindingResult.hasErrors()) {
				resp.setMessage("hasErrors " + bindingResult.getFieldError().getDefaultMessage());
				resp.setStatusCode(200);
				return resp;
			}
			if (optionalUser.isPresent()) {
				resp.setMessage("email taken");
				resp.setStatusCode(200);
				return resp;
			} else {
				User ourUsers = new User();
				ourUsers.setEmail(registrationRequest.getEmail());
				ourUsers.setName(registrationRequest.getName());
				ourUsers.setDob(registrationRequest.getDob());
				ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
//				ourUsers.setRole(registrationRequest.getRole());
				User ourUserResult = userRepository.save(ourUsers);
				if (ourUserResult != null && ourUserResult.getId() > 0) {
//					resp.setUser(ourUserResult);
					resp.setMessage("User Saved Successfully");
					resp.setStatusCode(200);
				}
			}

		} catch (Exception e) {
			resp.setStatusCode(500);
			resp.setError("ok error register " + e.getMessage());

		}
		return resp;
	}

	public Login signIn(Login signinRequest, BindingResult bindingResult) throws Exception {
		Login response = new Login();

		try {
			if (bindingResult.hasErrors()) {
				response.setError(bindingResult.getFieldError().getDefaultMessage());
				response.setStatusCode(200);
			} else {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
				var user = userRepository.findUserByEmail(signinRequest.getEmail()).orElseThrow();
				System.out.println("USER IS: " + user);
				var jwt = jwtUtils.generateToken(user);
				var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
				response.setStatusCode(200);
				response.setToken(jwt);
				response.setRefreshToken(refreshToken);
				response.setExpirationTime("24Hr");
				response.setMessage("Successfully Signed In");
			}

		} catch (AuthenticationException e) {
			// TODO: handle exception
			response.setStatusCode(200);
			response.setError("ok error Incorrect email or password" + e.getMessage());
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			response.setStatusCode(200);
			response.setError("ok error " + e.getMessage() + " Incorrect email or password");
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setError("ok error " + e.getMessage() + " email: " + response.getEmail());

		}
		return response;
	}

	public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
		ReqRes response = new ReqRes();
		String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
		User users = userRepository.findUserByEmail(ourEmail).orElseThrow();
		if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
			var jwt = jwtUtils.generateToken(users);
			response.setStatusCode(200);
			response.setToken(jwt);
			response.setRefreshToken(refreshTokenReqiest.getToken());
			response.setExpirationTime("24Hr");
			response.setMessage("Successfully Refreshed Token");
		}
		response.setStatusCode(500);
		response.setMessage(ourEmail + users.getName());
		return response;
	}
}
