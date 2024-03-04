package com.example.springmvc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.NoSuchElementException;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.springmvc.controller.api.admin.auth.FormLogin;
import com.example.springmvc.controller.api.admin.auth.FormRegister;
import com.example.springmvc.controller.api.user.auth.FormUserLogin;
import com.example.springmvc.controller.api.user.auth.FormUserRegister;
import com.example.springmvc.jwt.JWTUtils;
import com.example.springmvc.jwt.JwtUser;
import com.example.springmvc.message.ErrorMessage;
import com.example.springmvc.model.Role;
import com.example.springmvc.model.User;
import com.example.springmvc.repository.RoleRepository;
import com.example.springmvc.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class LoginService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private ModelMapper modelMapper;

	public JwtUser signInAdmin(FormLogin signinRequest, BindingResult bindingResult) throws Exception {
		JwtUser response = new JwtUser();
		try {
			if (bindingResult.hasErrors()) {
				response.setError(bindingResult.getFieldError().getDefaultMessage());
				response.setStatusCode(200);
			} else {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
				var user = userRepository.findUserByEmail(signinRequest.getEmail()).orElseThrow();

				if (user.getRole().getName().equals("USER") || user == null) {
					response.setError("Incorrect email or password" + " not admin");
					response.setStatusCode(200);
					return response;
				}
				System.out.println("USER IS: " + user);
				var jwt = jwtUtils.generateToken(user);
//				var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
				response.setStatusCode(200);
				response.setTokenAccess(jwt);
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

	public ErrorMessage register(@Valid FormRegister request, BindingResult bindingResult) {
		ErrorMessage message = new ErrorMessage();
		Optional<User> optionalUser = userRepository.findUserByEmail(request.getEmail());
		try {
			if (bindingResult.hasErrors()) {
				message.setMessage("hasErrors " + bindingResult.getFieldError().getDefaultMessage());
				message.setStatusCode(200);
				return message;
			}
			if (!request.getConfirmPassword().equals(request.getPassword())) {
				message.setMessage("hasErrors " + "Confirm password incorrect");
				message.setStatusCode(200);
				return message;
			}
			if (optionalUser.isPresent()) {
				message.setMessage("email taken");
				message.setStatusCode(200);
				return message;
			} else {
				Optional<Role> role = roleRepository.findById(request.getRole_id());
				if (role.isPresent()) {
					User ok = modelMapper.map(request, User.class);
					ok.setRole(role.get());
					ok.setPassword(passwordEncoder.encode(request.getPassword()));
					ok.setRole(role.get());
					ok.setActive(true);
					ok.setCreatedDate(LocalDateTime.now());
					User ourUserResult = userRepository.save(ok);
					if (ourUserResult != null && ourUserResult.getId() > 0) {
//						resp.setUser(ourUserResult);
						message.setMessage("User Saved Successfully ");
						message.setStatusCode(200);
						return message;
					}
				}
				message.setMessage("not found role");
				message.setStatusCode(200);

			}

		} catch (Exception e) {
			message.setStatusCode(500);
			message.setMessage("ok error register " + e.getMessage());

		}
		return message;
	}

	public JwtUser signInUser(FormUserLogin loginRequest, BindingResult bindingResult) {
		JwtUser response = new JwtUser();
		// TODO Auto-generated method stub
		try {
			if (bindingResult.hasErrors()) {
				response.setError(bindingResult.getFieldError().getDefaultMessage());
				response.setStatusCode(200);
			} else {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
				var user = userRepository.findUserByEmail(loginRequest.getEmail()).orElseThrow();

				if (!user.getRole().getName().equals("USER") || user == null) {
					response.setError("Incorrect email or password");
					response.setStatusCode(200);
					return response;
				}
				System.out.println("USER IS: " + user);
				var jwt = jwtUtils.generateToken(user);
//				var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
				response.setStatusCode(200);
				response.setTokenAccess(jwt);
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

	public ErrorMessage registerUser(FormUserRegister request, BindingResult bindingResult) {
		// TODO Auto-generated method stub
		ErrorMessage message = new ErrorMessage();
		Optional<User> optionalUser = userRepository.findUserByEmail(request.getEmail());
		try {
			if (bindingResult.hasErrors()) {
				message.setMessage("hasErrors " + bindingResult.getFieldError().getDefaultMessage());
				message.setStatusCode(200);
				return message;
			}
			if (!request.getConfirmPassword().equals(request.getPassword())) {
				message.setMessage("hasErrors " + "Confirm password incorrect");
				message.setStatusCode(200);
				return message;
			}
			if (optionalUser.isPresent()) {
				message.setMessage("email taken");
				message.setStatusCode(200);
				return message;
			} else {
				Optional<Role> role = roleRepository.findById((long) 2);
				if (role.isPresent() && role.get().getName().equals("USER")) {
					User user = modelMapper.map(request, User.class);
					user.setRole(role.get());
					user.setPassword(passwordEncoder.encode(request.getPassword()));
					user.setRole(role.get());
					user.setActive(true);
					user.setCreatedDate(LocalDateTime.now());
					User ourUserResult = userRepository.save(user);
					if (ourUserResult != null && ourUserResult.getId() > 0) {
//						resp.setUser(ourUserResult);
						message.setMessage("User Saved Successfully ");
						message.setStatusCode(200);
						return message;
					}
				}
				message.setMessage("not found role");
				message.setStatusCode(200);

			}

		} catch (Exception e) {
			message.setStatusCode(500);
			message.setMessage("ok error register " + e.getMessage());

		}
		return message;
	}

}
