package com.songzio.songzio.model;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

@Entity(name = "product_seq")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="productID") // indentityInfo để đánh dấu hiện 1 lần duy nhất khi gen 
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productID;
	
	@Size(min=4, message="Name of the product must be bigger than 4")
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_price")
	private String productPrice;
	
	@Column(name="product_image")
	private String productImage;
	
	@Column(name="product_count")
	private int productCount;
	
	@Column(name="product_stock")
	private Integer productStock;
	
	@OneToMany(mappedBy="product")
	@JsonIgnore
	private List<Order_Product> order_Products;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Product() {
		
	}

	public Product(Integer productID,
			@Size(min = 4, message = "Name of the product must be bigger than 4") String productName,
			String productPrice, String image, int bookCount, Integer productStock, List<Order_Product> order_Products,
			Category category) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImage = image;
		this.productCount = bookCount;
		this.productStock = productStock;
		this.order_Products = order_Products;
		this.category = category;
	}

	public Product(Integer productID,
			@Size(min = 4, message = "Name of the product must be bigger than 4") String productName,
			String productPrice, String image, int productCount, Integer productStock) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImage = image;
		this.productCount = productCount;
		this.productStock = productStock;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public List<Order_Product> getOrder_Products() {
		return order_Products;
	}

	public void setOrder_Products(List<Order_Product> order_Products) {
		this.order_Products = order_Products;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
		
}
