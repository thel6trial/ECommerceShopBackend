package com.songzio.songzio.formDTO;

public class ProductFormDTO {
	
	private String productName;
	
	private String productPrice;
	
	private Integer productStock;
	
	private String productImage;
	
	private String categoryName;
	
	private String promotionName;

	public ProductFormDTO(String productName, String productPrice, Integer productStock, String categoryName,
			String promotionName, String productImage) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.categoryName = categoryName;
		this.promotionName = promotionName;
		this.productImage = productImage;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

}
