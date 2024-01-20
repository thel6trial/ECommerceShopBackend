package com.songzio.songzio.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="user_role_seq")
public class User_Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer roleID;
	
	@Column(name="role_name")
	private String roleName;
	
	@OneToMany(mappedBy="user_role")
	@JsonIgnore
	private List<User> users;

	public User_Role() {
		
	}
	public User_Role(Integer roleID, String roleName) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "User_Role [roleID=" + roleID + ", roleName=" + roleName + "]";
	}
	
}
