package com.example.springmvc.dto;

import com.example.springmvc.model.Categories;

public class PhonesDTO {

	private Long id;
	private String name;
	private int price;
//	private Long category_id;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

//	public Long getCategory_id() {
//		return category_id;
//	}
//
//	public void setCategory_id(Long category_id) {
//		this.category_id = category_id;
//	}

}
