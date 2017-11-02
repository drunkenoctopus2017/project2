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
	private int sbsId;
	
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
	

	@Column(name="SBS_DESCRIPTION")
	private String descriptions;
	
	@Column(name="SBS_POINTS")
	private int points;
	
	@Column(name="SBL_ID")
	private int laneId;
	
	@Column(name="SBS_DONE")
	private String finishTime;
	
	public ScrumBoardStory() {
	}

	public int getSbsId() {
		return sbsId;
	}

	public void setSbsId(int sbsId) {
		this.sbsId = sbsId;
	}
/*
	public int getSbId() {
		return sbId;
	}

	public void setSbId(int sbId) {
		this.sbId = sbId;
	}
*/
	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
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

	@OneToMany(mappedBy="story", fetch=FetchType.EAGER)
	private List<ScrumBoardTask> tasks = new ArrayList<>();
	
    public List<ScrumBoardTask> getTasks() {
		return tasks;
	}
	
    public void setTasks(List<ScrumBoardTask> tasks) {
		this.tasks = tasks;
	}
    
	@Override
	public String toString() {
		return "SbStories [sbsId=" + sbsId + ", descriptions=" + descriptions + ", points=" + points
				+ ", laneId=" + laneId + ", finishTime=" + finishTime + "]";
	}
	
	
}
