package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SCRUM_USERS")
public class ScrumUser {
	@Id
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="USER_FN")
	private String userFn;
	
	@Column(name="USER_LN")
	private String userLn;
	
	@Column(name="USER_USERNAME")
	private String username;
	
	@Column(name="USER_PASSWORD")
	private String password;
	
	@Column(name="USER_EMAIL")
	private String email;
	
	@Column(name="ROLE_ID")
	private int roleId;
	
	public ScrumUser() {
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserFn() {
		return userFn;
	}
	public void setUserFn(String userFn) {
		this.userFn = userFn;
	}
	public String getUserLn() {
		return userLn;
	}
	public void setUserLn(String userLn) {
		this.userLn = userLn;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "ScrumUser [userId=" + userId + ", userFn=" + userFn + ", userLn=" + userLn + ", roleId=" + roleId + "]";
	}
	
	
}