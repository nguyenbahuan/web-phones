package com.example.springmvc.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
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
	@Length(min = 1, message = "name phone so short")
	@Column
	private String name;

	@NotNull(message = "required price")
	@Column
	private int price;

	@Column
	private String description;
	@Column
	private int totalQuantity;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private boolean isActive;

	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Categories categories;

	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
	private List<Images> images;

	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
	private List<ProductReviews> productReviews;

	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
	private List<OdersDetails> odersDetails;

	@ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
	private Set<Discounts> discounts = new HashSet<>();

	public Products() {

	}

	public Products(String name, String description, int price, Categories categories, int totalQuantity,
			LocalDateTime createdDate, boolean isActive) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.categories = categories;
		this.totalQuantity = totalQuantity;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
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

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	public List<ProductReviews> getProductReviews() {
		return productReviews;
	}

	public void setProductReviews(List<ProductReviews> productReviews) {
		this.productReviews = productReviews;
	}

	public List<OdersDetails> getOdersDetails() {
		return odersDetails;
	}

	public void setOdersDetails(List<OdersDetails> odersDetails) {
		this.odersDetails = odersDetails;
	}

	public Set<Discounts> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Set<Discounts> discounts) {
		this.discounts = discounts;
	}

}
