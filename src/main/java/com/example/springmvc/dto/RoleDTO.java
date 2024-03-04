package com.example.springmvc.dto;

import java.util.List;

import com.example.springmvc.model.User;

public class RoleDTO {
	private Long id;
	private String name;
//	private List<User> users;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
