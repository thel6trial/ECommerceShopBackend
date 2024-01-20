package com.songzio.songzio.formDTO;

public class PromotionFormDTO {
	
	private String promotionName;
	
	private String promotionType;
	
	private int promotionNumber;
	
	private int promotionCount;
		
	private String loyaltyName;
	
	private String promotionCode;

	public PromotionFormDTO(String promotionName, String promotionType, int promotionNumber, int promotionCount,
			String loyaltyName, String promotionCode) {
		super();
		this.promotionName = promotionName;
		this.promotionType = promotionType;
		this.promotionNumber = promotionNumber;
		this.promotionCount = promotionCount;
		this.loyaltyName = loyaltyName;
		this.promotionCode = promotionCode;
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

	public String getLoyaltyName() {
		return loyaltyName;
	}

	public void setLoyaltyName(String loyaltyName) {
		this.loyaltyName = loyaltyName;
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
