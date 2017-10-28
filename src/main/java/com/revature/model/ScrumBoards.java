package com.revature.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SCRUM_BOARDS")
public class ScrumBoards {
	@Id
	@Column(name="SB_ID")
	private int sbId;
	@Column(name="USER_ID")
	private int userId;
	@Column(name="SB_NAME")
	private String Name;
	@Column(name="SB_LENGTH")
	private int sbLength;

	@Column(name="SBSTART")
	private Timestamp startTime;
	
	public ScrumBoards() {
	}

	public int getSbId() {
		return sbId;
	}

	public void setSbId(int sbId) {
		this.sbId = sbId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	public int getSbLength() {
		return sbLength;
	}

	public void setSbLength(int sbLength) {
		this.sbLength = sbLength;
	}

	@Override
	public String toString() {
		return "ScrumBoards [sbId=" + sbId + ", userId=" + userId + ", Name=" + Name + ", startTime=" + startTime + "]";
	}
	
	
}
