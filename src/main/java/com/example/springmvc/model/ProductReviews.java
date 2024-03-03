package com.example.springmvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_reviews")
public class ProductReviews {
	@Id
	@GeneratedValue
	private Long id;
	private int rating;
	private String comment;
	
	@ManyToOne()
	@JoinColumn(name = "product_id", nullable = false)
	private Products products;
	
	@ManyToOne()
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	
	
}
