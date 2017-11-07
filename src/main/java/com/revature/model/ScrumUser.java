package com.revature.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SCRUM_USERS")
public class ScrumUser {
	
	@Id
	@SequenceGenerator(name="SU_SEQ", sequenceName="SU_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SU_SEQ")
	@Column(name="USER_ID")
	private int id;
	
	@Column(name="USER_FN")
	private String firstName;
	
	@Column(name="USER_LN")
	private String lastName;
	
	@Column(name="USER_USERNAME")
	private String username;
	
	@Column(name="USER_PASSWORD")
	private String password;
	
	@Column(name="USER_EMAIL")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private ScrumUserRole role;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="USERS_BOARDS",	joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"), inverseJoinColumns=@JoinColumn(name="SB_ID", referencedColumnName="SB_ID"))
	private List<ScrumBoard> scrumBoards;
	
	public ScrumUser() {}
	
	public ScrumUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public ScrumUser(int id, String firstName, String lastName, String username, String password, String email, ScrumUserRole role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		//this.roleId = roleId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public ScrumUserRole getRole() {
		return role;
	}

	public void setRole(ScrumUserRole role) {
		this.role = role;
	}
	
	public List<ScrumBoard> getScrumBoards() {
		return scrumBoards;
	}

	public void setScrumBoards(List<ScrumBoard> scrumBoards) {
		this.scrumBoards = scrumBoards;
	}
	
	@Override
	public String toString() {
		return "ScrumUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", role=" + role + " scrumBoards=" + scrumBoards.size() + "]";
	}
}
