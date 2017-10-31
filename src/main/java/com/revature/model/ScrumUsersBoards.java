package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS_BOARDS")
public class ScrumUsersBoards {
<<<<<<< HEAD:src/main/java/com/revature/model/ScrumUsersBoards.java
=======
	
>>>>>>> 2c376636a4203e3e26e6b8c3621e86e63bf0f089:src/main/java/com/revature/model/ScrumUsersBoards.java
	@Id
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="SB_ID")
	private int sbId;
	
	public ScrumUsersBoards() {
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
