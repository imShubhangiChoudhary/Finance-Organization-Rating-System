package com.frs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {

	@Id
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="entity_Type")
	private char entityType;
	
	@Column(name="id")
	private int id;
	
	public Login() {

	}

	
	/**
	 * @param userName
	 * @param password
	 * @param entityType
	 * @param id
	 */
	public Login(String userName, String password, char entityType, int id) {
		super();
		this.userName = userName;
		this.password = password;
		this.entityType = entityType;
		this.id = id;
	}


	/**
	 * @param userName
	 * @param password
	 * @param entityType
	 */
	public Login(String userName, String password, char entityType) {
		super();
		this.userName = userName;
		this.password = password;
		this.entityType = entityType;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the entityType
	 */
	public char getEntityType() {
		return entityType;
	}


	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(char entityType) {
		this.entityType = entityType;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password + ", entityType=" + entityType + ", id=" + id
				+ "]";
	}
	
	
	
}
