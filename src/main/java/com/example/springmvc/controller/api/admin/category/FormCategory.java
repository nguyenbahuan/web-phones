package com.example.springmvc.controller.api.admin.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FormCategory {
	private Long id;

	@NotBlank(message = "required name category")
	private String name;

	public FormCategory() {

	}

	public FormCategory(Long id, @NotBlank(message = "required name category") String name) {
		super();
		this.id = id;
		this.name = name;
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

}
