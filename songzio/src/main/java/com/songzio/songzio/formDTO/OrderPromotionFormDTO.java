package com.songzio.songzio.formDTO;

public class OrderPromotionFormDTO {
	private int orderID;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public OrderPromotionFormDTO(int orderID) {
		super();
		this.orderID = orderID;
	}

}
