package com.example.springmvc.model;

import java.util.Collection;
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
	
	private Date createdDate;
	private Date updatedDate;
	
	private boolean isActive;
	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Products> products;

	public Categories() {

	}

	public Categories(String name) {

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

	public List<Products> getPhones() {
		return products;
	}

	public void setPhones(List<Products> products) {
		this.products = products;
	}

}
