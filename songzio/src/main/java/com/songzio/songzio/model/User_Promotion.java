package com.songzio.songzio.model;

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

@Entity(name = "user_promotion_seq")
public class User_Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_promotion_id")
	private Integer userPromotionID;
	
	@Column(name="is_used")
	private boolean isUsed;
	
	@Column(name="time_used")
	private String timeUsed;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@Fetch(FetchMode.JOIN)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="promotion_id")
	@Fetch(FetchMode.JOIN)
	private Promotion promotion;
	
	public User_Promotion() {
		
	}

	public User_Promotion(Integer userPromotionID, boolean isUsed, String timeUsed) {
		super();
		this.userPromotionID = userPromotionID;
		this.isUsed = isUsed;
		this.timeUsed = timeUsed;
	}

	public User_Promotion(Integer userPromotionID, boolean isUsed, String timeUsed, User user, Promotion promotion) {
		super();
		this.userPromotionID = userPromotionID;
		this.isUsed = isUsed;
		this.timeUsed = timeUsed;
		this.user = user;
		this.promotion = promotion;
	}

	public Integer getUserPromotionID() {
		return userPromotionID;
	}

	public void setUserPromotionID(Integer userPromotionID) {
		this.userPromotionID = userPromotionID;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getTimeUsed() {
		return timeUsed;
	}

	public void setTimeUsed(String timeUsed) {
		this.timeUsed = timeUsed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

}
