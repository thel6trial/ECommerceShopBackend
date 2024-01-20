package com.songzio.songzio.model;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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

@Entity(name="promotion_seq")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="promotionID") // indentityInfo để đánh dấu hiện 1 lần duy nhất khi gen 
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="promotion_id")
	private Integer promotionID;
	
	@Column(name="promotion_name")
	private String promotionName;
	
	@Column(name="promotion_type")
	private String promotionType;
	
	@Column(name="promotion_number")
	private int promotionNumber;
	
	@Column(name="promotion_count")
	private int promotionCount;
	
	@Column(name="promotion_flag")
	private boolean promotionFlag;
	
	@Column(name="promotion_code")
	private String promotionCode;
	
	@OneToMany(mappedBy="promotion")
	private List<User_Promotion> user_promotions;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="loyalty_id")
	@Fetch(FetchMode.JOIN)
	private User_Loyalty user_loyalty;
	
	public Promotion() {
		
	}

	public Promotion(Integer promotionID, String promotionName, String promotionType, int promotionNumber, int promotionCount, boolean promotionFlag, String promotionCode) {
		super();
		this.promotionID = promotionID;
		this.promotionName = promotionName;
		this.promotionType = promotionType;
		this.promotionNumber = promotionNumber;
		this.promotionFlag = promotionFlag;
		this.promotionCode = promotionCode;
		this.promotionCount = promotionCount;
	}

	public Promotion(Integer promotionID, String promotionName, String promotionType, int promotionNumber,
			int promotionCount, boolean promotionFlag, String promotionCode, List<User_Promotion> user_promotions,
			User_Loyalty user_loyalty) {
		super();
		this.promotionID = promotionID;
		this.promotionName = promotionName;
		this.promotionType = promotionType;
		this.promotionNumber = promotionNumber;
		this.promotionCount = promotionCount;
		this.promotionFlag = promotionFlag;
		this.promotionCode = promotionCode;
		this.user_promotions = user_promotions;
		this.user_loyalty = user_loyalty;
	}

	public Integer getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(Integer promotionID) {
		this.promotionID = promotionID;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public int getPromotionNumber() {
		return promotionNumber;
	}

	public void setPromotionNumber(int promotionNumber) {
		this.promotionNumber = promotionNumber;
	}

	public List<User_Promotion> getUser_promotions() {
		return user_promotions;
	}

	public void setUser_promotions(List<User_Promotion> user_promotions) {
		this.user_promotions = user_promotions;
	}

	public boolean isPromotionFlag() {
		return promotionFlag;
	}

	public void setPromotionFlag(boolean promotionFlag) {
		this.promotionFlag = promotionFlag;
	}

	public User_Loyalty getUser_loyalty() {
		return user_loyalty;
	}

	public void setUser_loyalty(User_Loyalty user_loyalty) {
		this.user_loyalty = user_loyalty;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public int getPromotionCount() {
		return promotionCount;
	}

	public void setPromotionCount(int promotionCount) {
		this.promotionCount = promotionCount;
	}
}
