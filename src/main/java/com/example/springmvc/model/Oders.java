package com.example.springmvc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "oders")
public class Oders {
	@Id
	@GeneratedValue
	private Long id;
	
	private String payMethod;
	
	private double total;
	
	private String status;
	
	private Date createdDate;
	
	@OneToMany(mappedBy = "oders" , cascade = CascadeType.ALL)
    private List<OdersDetails>  odersDetails;

	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	

	
	
}
