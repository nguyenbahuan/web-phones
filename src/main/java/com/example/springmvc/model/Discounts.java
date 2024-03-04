package com.example.springmvc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "discounts")
public class Discounts {
	@Id
	@GeneratedValue
	private Long id;

	private float discountPercentage;

	private String discountName;

	private Date startDate;
	private Date endDate;

	private Date createdDate;

	private Date updatedDate;

	private boolean isActive;

	@ManyToMany
	@JoinTable(name = "discount_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "discount_id"))
	private Set<Products> products = new HashSet<>();

}
