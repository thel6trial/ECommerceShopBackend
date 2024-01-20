package com.songzio.songzio.formDTO;

public class RegisterFormDTO {
	
	private String username;
	
	private String user_password;
	
	private String user_birthday;
	
	private String user_phone;
	
	public RegisterFormDTO(String username, String user_password, String user_birthday, String user_phone) {
		super();
		this.username = username;
		this.user_password = user_password;
		this.user_birthday = user_birthday;
		this.user_phone = user_phone;

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

}
