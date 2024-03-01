package com.example.springmvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "images")
public class Images {
	@Id
	@GeneratedValue
	private Long id;
	
	private String filePath;
	

	@ManyToOne()
	@JoinColumn(name = "product_id", nullable = false)
	private Products products;
	
}
