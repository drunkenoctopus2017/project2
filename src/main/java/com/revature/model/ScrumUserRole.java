package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SU_ROLES")
public class ScrumUserRole {
	
	@Id
	@Column(name="ROLE_ID")
	private int id;
	
	@Column(name="ROLE_NAME")
	private String roleName;
	
	public ScrumUserRole() {
		// TODO Auto-generated constructor stub
	}
	public ScrumUserRole(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "SuRoles [id=" + id + ", roleName=" + roleName + "]";
	}
	
}
