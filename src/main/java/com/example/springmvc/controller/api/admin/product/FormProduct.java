package com.example.springmvc.controller.api.admin.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FormProduct {
	private Long id;

	@NotBlank(message = "required name product")
	private String name;

	@NotNull(message = "required price")
	@Min(value = 1000, message = "price is so short")
	@Max(value = 99999999, message = "price is so hiegt")
	private int price;

	private Long category_id;

	public FormProduct() {

	}

	public FormProduct(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

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

}
