package com.revature.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SB_TASKS")
public class ScrumBoardTask {
	@Id
	@Column(name="SBT_ID")
	private int id;
	
	//@Column(name="SBS_ID")
	//private int sbsId;
	
	@Column(name="SBT_STATUS")
	private int status; //or boolean? 0=incomplete 1= complete
	
	@Column(name="SBT_DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="SBS_ID")
	private ScrumBoardStory story;
	
	@JsonIgnore
	public ScrumBoardStory getStory() {
		return story;
	}
	
	public void setStory(ScrumBoardStory story) {
		this.story = story;
	}
	
	public ScrumBoardTask() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ScrumBoardTask [id=" + id + ", status=" + status + ", description=" + description + ", story=" + (story != null ? story.getId() : " no story parent found")
				+ "]";
	}

	//public int getSbsId() {
	//	return sbsId;
	//}

	//public void setSbsId(int sbsId) {
	//	this.sbsId = sbsId;
	//}
	
}
