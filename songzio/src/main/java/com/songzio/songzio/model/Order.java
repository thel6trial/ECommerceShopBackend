package com.songzio.songzio.model;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "order_seq")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderID;
    
	@Column(name="order_price")
	private int orderPrice;
	
	@Column(name="order_time")
	private String orderTime;
	
	@Column(name="order_discount")
	private int orderDiscount;
	
	@Column(name="order_pay")
	private String orderPay;
	
	@OneToMany(mappedBy = "order")
	@JsonIgnore
	private List<Order_Product> order_Products;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@Fetch(FetchMode.JOIN)
	private User user;

	public Order(Integer orderID, int orderPrice, String orderTime, int orderDiscount, String orderPay) {
		super();
		this.orderID = orderID;
		this.orderPrice = orderPrice;
		this.orderTime = orderTime;
		this.orderDiscount = orderDiscount;
		this.orderPay = orderPay;
	}
	
	public Order(Integer orderID, int orderPrice, String orderTime, int orderDiscount, String orderPay,
			List<Order_Product> order_Products, User user) {
		super();
		this.orderID = orderID;
		this.orderPrice = orderPrice;
		this.orderTime = orderTime;
		this.orderDiscount = orderDiscount;
		this.orderPay = orderPay;
		this.order_Products = order_Products;
		this.user = user;
	}

	public Order() {
		
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(int orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public String getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(String orderPay) {
		this.orderPay = orderPay;
	}

	public List<Order_Product> getOrder_Products() {
		return order_Products;
	}

	public void setOrder_Products(List<Order_Product> order_Products) {
		this.order_Products = order_Products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
