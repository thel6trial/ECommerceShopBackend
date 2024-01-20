package com.songzio.songzio.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="user_loyalty_seq")
public class User_Loyalty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="loyalty_id")
	private Integer loyaltyID;
	
	@Column(name="loyalty_name")
	private String loyaltyName;
	
	@Column(name="loyalty_condition")
	private String loyaltyCondition;
	
	@OneToMany(mappedBy="user_loyalty")
	@JsonIgnore
	private List<User> users;
	
	@OneToMany(mappedBy="user_loyalty")
	@JsonIgnore 
	private List<Promotion> promotions;
	
	public User_Loyalty() {
		
	}

	public User_Loyalty(Integer loyaltyID, String loyaltyName, String loyaltyCondition) {
		super();
		this.loyaltyID = loyaltyID;
		this.loyaltyName = loyaltyName;
		this.loyaltyCondition = loyaltyCondition;
	}

	public User_Loyalty(Integer loyaltyID, String loyaltyName, String loyaltyCondition, List<User> users,
			List<Promotion> promotions) {
		super();
		this.loyaltyID = loyaltyID;
		this.loyaltyName = loyaltyName;
		this.loyaltyCondition = loyaltyCondition;
		this.users = users;
		this.promotions = promotions;
	}



	public Integer getLoyaltyID() {
		return loyaltyID;
	}

	public void setLoyaltyID(Integer loyaltyID) {
		this.loyaltyID = loyaltyID;
	}

	public String getLoyaltyName() {
		return loyaltyName;
	}

	public void setLoyaltyName(String loyaltyName) {
		this.loyaltyName = loyaltyName;
	}

	public String getLoyaltyCondition() {
		return loyaltyCondition;
	}

	public void setLoyaltyCondition(String loyaltyCondition) {
		this.loyaltyCondition = loyaltyCondition;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	
}
