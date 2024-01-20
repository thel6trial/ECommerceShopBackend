package com.songzio.songzio.model;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="category_seq")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryID;
	
	@Column(name="category_name")
	private String categoryName;
	
	@Column(name="category_count")
	private int categoryCount;
	
	@OneToMany(mappedBy="category", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Product> products;
	
	public Category() {
		
	}

	public Category(Integer categoryID, String categoryName, int categoryCount, List<Product> products) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.products = products;
		this.categoryCount = categoryCount;
	}
	
	public Category(Integer categoryID, String categoryName, int categoryCount) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.categoryCount = categoryCount;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(int categoryCount) {
		this.categoryCount = categoryCount;
	}
	
}
