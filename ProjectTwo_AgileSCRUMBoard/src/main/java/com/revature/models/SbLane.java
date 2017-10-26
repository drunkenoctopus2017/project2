package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SB_LANES")
public class SbLane {
	@Id
	@Column(name="SBL_ID")
	private int sblId;
	
	@Column(name="SBL_NAME")
	private String sblName;
	
	public SbLane() {
	}

	public int getSblId() {
		return sblId;
	}

	public void setSblId(int sblId) {
		this.sblId = sblId;
	}

	public String getSblName() {
		return sblName;
	}

	public void setSblName(String sblName) {
		this.sblName = sblName;
	}

	@Override
	public String toString() {
		return "SbLane [sblId=" + sblId + ", sblName=" + sblName + "]";
	}
	
	
}
