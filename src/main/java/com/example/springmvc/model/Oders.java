package com.example.springmvc.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "oders")
public class Oders {
	@Id
	@GeneratedValue
	private Long id;
	
	
	@ManyToMany()
    private Set<Products> products = new HashSet<>();

	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	

	@ManyToMany()
    private Set<Discount> discounts  = new HashSet<>();
	
	
}
