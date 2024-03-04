package com.example.springmvc.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue
	private Long id;

	private String address;

	private Date createdDate;

	private Date updatedDate;

	private boolean isActive;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	
}
