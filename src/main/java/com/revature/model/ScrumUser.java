package com.revature.model;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
=======
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
>>>>>>> 2c376636a4203e3e26e6b8c3621e86e63bf0f089
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SCRUM_USERS")
public class ScrumUser {
	/*
	USER_ID
	USER_FN
	USER_LN
	USER_USERNAME
	USER_PASSWORD
	ROLE_ID
	USER_EMAIL
	*/
	
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
	
	@Column(name="ROLE_ID")
	private int role; //potentially changes to string via lookup table???
	
	//TODO add the boards and do greedy load since only one user will be logged in at a time but we'll need to show all their boards.
<<<<<<< HEAD
	//@OneToMany(mappedBy="bankUser", fetch=FetchType.EAGER) 
	//Set<ScrumBoards> scrumBoards = new HashSet<ScrumBoards>(); 	
=======
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USERS_BOARDS", 
	joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"), 
	inverseJoinColumns=@JoinColumn(name="SB_ID", referencedColumnName="SB_ID")) 
	private List<ScrumBoard> scrumBoards; 	
>>>>>>> 2c376636a4203e3e26e6b8c3621e86e63bf0f089
	
	public ScrumUser() {}
	
	public ScrumUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public ScrumUser(int id, String firstName, String lastName, String username, String password, String email, int role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
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
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

<<<<<<< HEAD
	@Override
	public String toString() {
		return "ScrumUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", role=" + role + "]";
=======
	public List<ScrumBoard> getScrumBoards() {
		return scrumBoards;
	}

	public void setScrumBoards(List<ScrumBoard> scrumBoards) {
		this.scrumBoards = scrumBoards;
	}

	@Override
	public String toString() {
		return "ScrumUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", role=" + role + ", scrumBoards=" + scrumBoards
				+ "]";
>>>>>>> 2c376636a4203e3e26e6b8c3621e86e63bf0f089
	}
}
