package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SU_ROLES")
public class SuRoles {
	@Id
	@Column(name="SUR_ID")
	private int roleId;
	
	@Column(name="SUR_name")
	private String roleName;
	
	public SuRoles() {
		// TODO Auto-generated constructor stub
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "SuRoles [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
}
