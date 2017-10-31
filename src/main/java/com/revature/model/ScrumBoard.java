package com.revature.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SCRUM_BOARDS")
public class ScrumBoard {
	
	@Id
	@Column(name="SB_ID")
	private int id;
	
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="SB_NAME")
	private String name;
	
	@Column(name="SB_DURATION")
	private int duration;

	@Column(name="SB_START")
	private Date startDate;
	
	public ScrumBoard() {
	}

	public int getSbId() {
		return id;
	}

	public void setSbId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startDate;
	}

	public void setStartTime(Date startDate) {
		this.startDate = startDate;
	}
	
	public int getSbLength() {
		return duration;
	}

	public void setSbLength(int duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return "ScrumBoard [id=" + id + ", userId=" + userId + ", name=" + name + ", duration=" + duration
				+ ", startDate=" + startDate + "]";
	}
	
	
}
