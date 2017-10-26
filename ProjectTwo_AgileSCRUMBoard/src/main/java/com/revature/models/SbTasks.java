package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SB_TASKS")
public class SbTasks {
	@Id
	@Column(name="SBT_ID")
	private int sbtId;
	@Column(name="STATUS")
	private int status; //or boolean? 0=incomplete 1= complete
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="SBS_ID")
	private int sbsId;

	public SbTasks() {
	}

	public int getSbtId() {
		return sbtId;
	}

	public void setSbtId(int sbtId) {
		this.sbtId = sbtId;
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

	public int getSbsId() {
		return sbsId;
	}

	public void setSbsId(int sbsId) {
		this.sbsId = sbsId;
	}
	@Override
	public String toString() {
		return "SbTasks [sbtId=" + sbtId + ", status=" + status + ", description=" + description + "]";
	}
}
