package com.example.springmvc.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.message.ErrorMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/")
public class WebController {
	@GetMapping("/")
	public ErrorMessage home() {
		return new ErrorMessage(200,"hello");
	}
//	@GetMapping()
//	public ErrorMessage home() {
//		return new ErrorMessage(200,"hello");
//	}

	@GetMapping("home")
	public ErrorMessage homie() {
		return new ErrorMessage(200,"hello");
	}

}
