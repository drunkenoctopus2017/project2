package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SB_LANES")
public class ScrumBoardLane {
	@Id
	@Column(name="SBL_ID")
	private int id;
	
	@Column(name="SBL_NAME")
	private String name;
	
	public ScrumBoardLane() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SbLane [id=" + id + ", name=" + name + "]";
	}
	
	
}
