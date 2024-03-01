package com.example.springmvc.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoriesDTO {
	private Long id;
	private String name;
	private List<PhonesDTO> phones;

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

	public List<PhonesDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhonesDTO> phones) {
		this.phones = phones;
	}

//
}
