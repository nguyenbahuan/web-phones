package com.example.springmvc.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "categories")
public class Categories {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;

//

	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	private boolean isActive;
	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Products> products;

	public Categories() {

	}

	public Categories(String name, LocalDateTime createdDate, boolean isActive) {

		this.name = name;
		this.createdDate = createdDate;
		this.isActive = isActive;
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

	public List<Products> getPhones() {
		return products;
	}

	public void setPhones(List<Products> products) {
		this.products = products;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

}
