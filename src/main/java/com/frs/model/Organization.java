package com.frs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
public class Organization {
	@Id
	@GeneratedValue
	@Column(name = "org_id")
	private int orgId;

	@Column(name = "org_name")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{5,}", message = "Organization name should starts with capital letter and contains only characters, digits are not allowed")
	private String orgName;

	@Column(name = "org_rating")
	private String orgRating;

	@Column(name = "org_description")
	private String orgDescription;

	
	@Pattern(regexp="[a-z]{5,}[0-9]{1,}@(gmail|yahoo|org|co).(com|in)")
	@Column(name = "Email")
	private String email;

	@DecimalMin(value = "1.0", message = "interest rate greater than or equal to 1.0 allowed")
	@DecimalMax(value = "10.0", message = "interest rate upto 10.0 allowed")
	@Column(name = "interest_rate")
	private double interestRate;

	@Pattern(regexp = "national|international|local|National|International|Local|state|State", message = " values must be national, international, local, National, International, Local, state, State")
	@Column(name = "scope")
	private String scope;

	@Min(value = 5000, message = "minimum gross income must be greater than 5000")
	@Max(value = 1000000, message = "maximum gross income must be less than 100000")
	@Column(name = "gross_income")
	private double grossIncome;

	@Pattern(regexp = "A|A+|A++|B|B+|B++|C|C+|C++", message = " values must be A, A+, A++, B, B+, B++, C, C+, C++")
	
	@Column(name = "org_user_rating")
	private String orgUserRating;

	@Column(name = "Status")
	private String status;

	/**
	 * @param orgName
	 * @param orgRating
	 * @param orgDescription
	 * @param email
	 * @param interestRate
	 * @param scope
	 * @param grossIncome
	 * @param orgUserRating
	 * @param status
	 */
	public Organization(String orgName, String orgRating, String orgDescription, String email, double interestRate,
			String scope, double grossIncome, String orgUserRating, String status) {
		super();
		this.orgName = orgName;
		this.orgRating = orgRating;
		this.orgDescription = orgDescription;
		this.email = email;
		this.interestRate = interestRate;
		this.scope = scope;
		this.grossIncome = grossIncome;
		this.orgUserRating = orgUserRating;
		this.status = status;
	}

	/**
	 * @param orgId
	 * @param orgName
	 * @param orgRating
	 * @param orgDescription
	 * @param email
	 * @param interestRate
	 * @param scope
	 * @param grossIncome
	 * @param orgUserRating
	 * @param status
	 */
	public Organization(int orgId, String orgName, String orgRating, String orgDescription, String email,
			double interestRate, String scope, double grossIncome, String orgUserRating, String status) {
		super();
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgRating = orgRating;
		this.orgDescription = orgDescription;
		this.email = email;
		this.interestRate = interestRate;
		this.scope = scope;
		this.grossIncome = grossIncome;
		this.orgUserRating = orgUserRating;
		this.status = status;
	}

	public Organization() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the orgId
	 */
	public int getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the orgRating
	 */
	public String getOrgRating() {
		return orgRating;
	}

	/**
	 * @param orgRating the orgRating to set
	 */
	public void setOrgRating(String orgRating) {
		this.orgRating = orgRating;
	}

	/**
	 * @return the orgDescription
	 */
	public String getOrgDescription() {
		return orgDescription;
	}

	/**
	 * @param orgDescription the orgDescription to set
	 */
	public void setOrgDescription(String orgDescription) {
		this.orgDescription = orgDescription;
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
	 * @return the interestRate
	 */
	public double getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the grossIncome
	 */
	public double getGrossIncome() {
		return grossIncome;
	}

	/**
	 * @param grossIncome the grossIncome to set
	 */
	public void setGrossIncome(double grossIncome) {
		this.grossIncome = grossIncome;
	}

	/**
	 * @return the org_user_rating
	 */
	public String getOrgUserRating() {
		return orgUserRating;
	}

	/**
	 * @param org_user_rating the org_user_rating to set
	 */
	public void setOrgUserRating(String orgUserRating) {
		this.orgUserRating = orgUserRating;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Organization [orgId=" + orgId + ", orgName=" + orgName + ", orgRating=" + orgRating
				+ ", orgDescription=" + orgDescription + ", email=" + email + ", interestRate=" + interestRate
				+ ", scope=" + scope + ", grossIncome=" + grossIncome + ", orgUserRating=" + orgUserRating
				+ ", status=" + status + "]";
	}

	public Organization orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
