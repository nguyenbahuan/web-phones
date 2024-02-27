package com.example.springmvc.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.payload.RandomStuff;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class WebController {
	@GetMapping("/")
	public RandomStuff home() {
		return new RandomStuff("home");
	}

	@GetMapping("/home")
	public RandomStuff homie() {
		return new RandomStuff("home");
	}

}
