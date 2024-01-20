package com.songzio.songzio.formDTO;

public class OrderFormDTO {
	
	public String orderPay;
	
	public String orderDiscountCode;

	public OrderFormDTO(String orderPay, String orderDiscountCode) {
		super();
		this.orderPay = orderPay;
		this.orderDiscountCode = orderDiscountCode;
	}

	public String getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(String orderPay) {
		this.orderPay = orderPay;
	}

	public String getOrderDiscountCode() {
		return orderDiscountCode;
	}

	public void setOrderDiscountCode(String orderDiscountCode) {
		this.orderDiscountCode = orderDiscountCode;
	}
	
}
