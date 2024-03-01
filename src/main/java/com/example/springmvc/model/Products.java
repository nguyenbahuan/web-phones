package com.example.springmvc.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "products")
public class Products {
	@Id
	@GeneratedValue
	private long id;

	
	@NotNull(message = "required name")
	@Length(min = 1 , message = "name phone so short")
	@Column
	String name;

	@NotNull(message = "required price")
	@Column
	int price;

	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Categories categories;
	
	
	@ManyToMany
    @JoinTable(
        name = "product_bill",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "bill_id")
    )
    private Set<Bills> bills = new HashSet<>();

	@OneToMany()
	private List<Images> images;
	
	public Products() {

	}

	public Products(String name, int price, Categories categories) {
		this.name = name;
		this.price = price;
		this.categories = categories;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
