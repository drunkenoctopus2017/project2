package com.revature.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SCRUM_BOARDS")
public class ScrumBoard {
	
	@Id
	@Column(name="SB_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SB_SEQ")
	@SequenceGenerator(name="SB_SEQ", sequenceName="SB_SEQ")
	private int id;
	
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="SB_NAME")
	private String name;
	
	@Column(name="SB_START")
	private Date startDate;
	
	@Column(name="SB_DURATION")
	private int duration;
	
	@OneToMany(mappedBy="scrumBoard", fetch=FetchType.EAGER)
	private List<ScrumBoardStory> stories = new ArrayList<>();
	
    public List<ScrumBoardStory> getStories() {
		return stories;
	}
	
    public void setStories(List<ScrumBoardStory> stories) {
		this.stories = stories;
	}
	
	@ManyToMany(mappedBy="scrumBoards")
	private List<ScrumUser> scrumUsers;
	
	//@JsonIgnore
    public List<ScrumUser> getScrumUsers() {
		return scrumUsers;
	}
	
    public void setScrumUsers(List<ScrumUser> scrumUsers) {
		this.scrumUsers = scrumUsers;
	}
    
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
		return "ScrumBoard [id=" + id + ", userId=" + userId + ", name=" + name + ", startDate=" + startDate
				+ ", duration=" + duration + "]";
	}
}
