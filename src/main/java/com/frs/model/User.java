package com.frs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User_master")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "User_id")
	int userId;

	@Size(min = 3, message = "First Name should not be Empty : min 3 characters required")
	@Column(name = "First_Name")
	private String firstName;

	@Size(min = 3, message = "Last Name should not be Empty : min 3 characters required")
	@Column(name = "Last_Name")
	private String lastName;

	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).{8,}$", message = "Password must contains Minimum eight characters, at least one letter, one number and one special character")
	@Column(name = "Password")
	private String password;

	@Pattern(regexp="[a-z]{5,}[0-9]{1,}@(gmail|yahoo|org|co).(com|in)")
	@Column(name = "Email_Id")
	private String email;

	@Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile No starts with 7 or 8 or 9 then contains 9 digits")
	@Column(name = "Mobile_No")
	private String mobileNo;

	@NotEmpty(message = "City name should not be empty")
	@Size(min = 3, message = "min 3 chars required")
	@Column(name = "City")
	private String city;

	public User() {
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
	 * @param mobileNo
	 * @param city
	 */
	public User(String firstName, String lastName, String password, String email, String mobileNo, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.mobileNo = mobileNo;
		this.city = city;
	}

	/**
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
	 * @param mobileNo
	 * @param city
	 */
	public User(int userId, String firstName, String lastName, String password, String email, String mobileNo,
			String city) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.mobileNo = mobileNo;
		this.city = city;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", email=" + email + ", mobileNo=" + mobileNo + ", city=" + city + "]";
	}

}