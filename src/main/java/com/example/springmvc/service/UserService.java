package com.example.springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springmvc.config.CustomUserDetails;
import com.example.springmvc.dto.RoleDTO;
import com.example.springmvc.dto.UserDTO;
//import com.example.springmvc.config.CustomUserDetails;
import com.example.springmvc.model.User;
import com.example.springmvc.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.experimental.var;

@Service
public class UserService implements UserDetailsService {
	private final UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//	private PasswordEncoder passwordEncoder;
	public void saveUser(User user) {
		userRepository.save(user);
	}
	public Optional<User> findUserbyEmail(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		if (user.isPresent()) {
			return user;
		} else {

			return user;
		}
		
	}

	public User loadUserByEmail(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		if (user.isPresent()) {
			return user.get();
		} else {

			throw new IllegalStateException("email not find");
		}

	}

	public User loadUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		} else {

			throw new IllegalStateException("user not find");
		}

	}

	public List<UserDTO> getUsers() {
		List<UserDTO> list = new ArrayList<>();
		List<User> users = userRepository.findAll();
		users.forEach(u -> {
			UserDTO user = modelMapper.map(u, UserDTO.class);
			user.setRole(modelMapper.map(u.getRole(), RoleDTO.class));
			list.add(user);
		});
//		 modelMapper.
		return list;
	}

	public void addNewUser(User user) {
		Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
		if (optionalUser.isPresent()) {
			throw new IllegalStateException("email taken");
		} else {
//			String pass = this.passwordEncoder.encode(user.getPassword());
//			user.setPassword(pass);
			userRepository.save(user);
		}

	}

	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		boolean exits = userRepository.existsById(userId);
		if (!exits) {
			throw new IllegalStateException("user " + userId + " don't exists");
		} else {
			userRepository.deleteById(userId);
		}
	}

	@Transactional
	public void updateUser(Long userId, String name, String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("user " + userId + " don't exists"));

		if (name != null && name.length() > 0 && !user.getName().equals(name)) {
			user.setName(name);
		}
		if (email != null && email.length() > 0 && !user.getEmail().equals(email)) {
			user.setEmail(email);
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findUserByEmail(username);
		return user.orElseThrow();

	}

}
