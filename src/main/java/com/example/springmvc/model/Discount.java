package com.example.springmvc.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "discounts")
public class Discount {
	@Id
	@GeneratedValue
	private Long id;

	private String discountCode;

	private float discountPercentage;
	

	@ManyToMany
    @JoinTable(
        name = "discount_oder",
        joinColumns = @JoinColumn(name = "oder_id"),
        inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    private Set<Oders> oders = new HashSet<>();
	

}
