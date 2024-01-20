package com.songzio.songzio.formDTO;

public class UserFormDTO {
	
	private String username;
	
	private String user_password;
	
	private String user_birthday;
	
	private String user_phone;
	
	private Integer user_total_payment;
	
	private String user_role_name;
	
	private String user_loyalty_name;

	public UserFormDTO(String username, String user_password, String user_birthday, String user_phone,
			Integer user_total_payment, String user_role_name, String user_loyalty_name) {
		super();
		this.username = username;
		this.user_password = user_password;
		this.user_birthday = user_birthday;
		this.user_phone = user_phone;
		this.user_total_payment = user_total_payment;
		this.user_role_name = user_role_name;
		this.user_loyalty_name = user_loyalty_name;
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

	public Integer getUser_total_payment() {
		return user_total_payment;
	}

	public void setUser_total_payment(Integer user_total_payment) {
		this.user_total_payment = user_total_payment;
	}

	public String getUser_role_name() {
		return user_role_name;
	}

	public void setUser_role_name(String user_role_name) {
		this.user_role_name = user_role_name;
	}

	public String getUser_loyalty_name() {
		return user_loyalty_name;
	}

	public void setUser_loyalty_name(String user_loyalty_name) {
		this.user_loyalty_name = user_loyalty_name;
	}

}
