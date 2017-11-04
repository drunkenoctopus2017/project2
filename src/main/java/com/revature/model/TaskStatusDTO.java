package com.revature.model;

/**
 * A kludgy DTO for update task status.
 * 
 * @author jpwru
 *
 */
public class TaskStatusDTO {
	public int id;
	public int status;
	
	@Override
	public String toString() {
		return "TastStatusDTO [id=" + id + ", status=" + status + "]";
	}
}
