package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS_BOARDS")
public class UserBoards {
	@Id
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="SB_ID")
	private int sbId;
	
	public UserBoards() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSbId() {
		return sbId;
	}

	public void setSbId(int sbId) {
		this.sbId = sbId;
	}

	@Override
	public String toString() {
		return "UserBoards [userId=" + userId + ", sbId=" + sbId + "]";
	}
	
	
}
