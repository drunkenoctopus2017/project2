package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SB_STORIES")
public class ScrumBoardStory {
	
	@Id
	@Column(name="SBS_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="SB_ID")
	private ScrumBoard scrumBoard;
	
	@JsonIgnore
	public ScrumBoard getScrumBoard() {
		return scrumBoard;
	}

	public void setScrumBoard(ScrumBoard scrumBoard) {
		this.scrumBoard = scrumBoard;
	}
	
	@OneToMany(mappedBy="story", fetch=FetchType.EAGER)
	private List<ScrumBoardTask> tasks = new ArrayList<>();
	
    public List<ScrumBoardTask> getTasks() {
		return tasks;
	}
	
    public void setTasks(List<ScrumBoardTask> tasks) {
		this.tasks = tasks;
	}

	@Column(name="SBS_DESCRIPTION")
	private String description;
	
	@Column(name="SBS_POINTS")
	private int points;
	
	@Column(name="SBL_ID")
	private int laneId;
	
	@Column(name="SBS_DONE")
	private String finishTime;
	
	public ScrumBoardStory() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
/*
	public int getSbId() {
		return sbId;
	}

	public void setSbId(int sbId) {
		this.sbId = sbId;
	}
*/
	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLaneId() {
		return laneId;
	}

	public void setLaneId(int laneId) {
		this.laneId = laneId;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	@Override
	public String toString() {
		return "ScrumBoardStory [id=" + id + ", description=" + description + ", points=" + points + ", laneId="
				+ laneId + ", finishTime=" + finishTime + ", tasks=" + tasks + "]";
	}
    
	
	
	
}
