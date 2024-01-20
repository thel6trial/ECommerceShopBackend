package com.songzio.songzio.model;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

@Entity(name="user_seq")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userID") 
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userID;
	
	private String username;
	
	private String user_password;
	
	private String user_birthday;
	
	private String user_phone;
	
	private int user_total_payment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role_id")
	@Fetch(FetchMode.JOIN)
	private User_Role user_role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="loyalty_id")
	@Fetch(FetchMode.JOIN)
	private User_Loyalty user_loyalty;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<User_Promotion> user_promotions;
	
	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	public User() {
		
	}

	public User(Integer userID, String username, String user_password, String user_birthday, String user_phone, int user_total_payment) {
		super();
		this.userID = userID;
		this.username = username;
		this.user_password = user_password;
		this.user_birthday = user_birthday;
		this.user_phone = user_phone;
		this.user_total_payment = user_total_payment;
	}

	public User(Integer userID, String username, String user_password, String user_birthday, String user_phone,
			int user_total_payment, User_Role user_role, User_Loyalty user_loyalty,
			List<User_Promotion> user_promotions, List<Order> orders) {
		super();
		this.userID = userID;
		this.username = username;
		this.user_password = user_password;
		this.user_birthday = user_birthday;
		this.user_phone = user_phone;
		this.user_total_payment = user_total_payment;
		this.user_role = user_role;
		this.user_loyalty = user_loyalty;
		this.user_promotions = user_promotions;
		this.orders = orders;
	}



	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public int getUser_total_payment() {
		return user_total_payment;
	}

	public void setUser_total_payment(int user_total_payment) {
		this.user_total_payment = user_total_payment;
	}

	public User_Role getUser_role() {
		return user_role;
	}

	public void setUser_role(User_Role user_role) {
		this.user_role = user_role;
	}

	public User_Loyalty getUser_loyalty() {
		return user_loyalty;
	}

	public void setUser_loyalty(User_Loyalty user_loyalty) {
		this.user_loyalty = user_loyalty;
	}

	public List<User_Promotion> getUser_promotions() {
		return user_promotions;
	}

	public void setUser_promotions(List<User_Promotion> user_promotions) {
		this.user_promotions = user_promotions;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
