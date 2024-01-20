package com.songzio.songzio.formDTO;

public class CategoryFormDTO {
	
	private String categoryName;
	
	private int categoryCount;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(int categoryCount) {
		this.categoryCount = categoryCount;
	}

	public CategoryFormDTO(String categoryName, int categoryCount) {
		super();
		this.categoryName = categoryName;
		this.categoryCount = categoryCount;
	}

}
