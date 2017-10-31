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
	
	@Column(name="SBSTART")
	private Date startDate;
	
	@Column(name="SB_DURATION")
	private int duration;
	
	public ScrumBoard() {
	}
	
	public ScrumBoard(String name, Date startDate, int duration) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.duration = duration;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "ScrumBoards [id=" + id + ", userId=" + userId + ", name=" + name + ", startDate=" + startDate + "]";
	}
	
	
}
