package com.frs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Feedback {
	@Id
	@GeneratedValue
	int id;
	int userId;
	int organizationId;
	String feedbackVal;

	public Feedback() {

	}
	

	/**
	 * @param id
	 * @param userId
	 * @param organizationId
	 * @param feedbackVal
	 */
	public Feedback(int id, int userId, int organizationId, String feedbackVal) {
		super();
		this.id = id;
		this.userId = userId;
		this.organizationId = organizationId;
		this.feedbackVal = feedbackVal;
	}


	/**
	 * @param user_id
	 * @param org_id
	 * @param feedbackVal
	 */
	public Feedback(int userId, int organizationId, String feedbackVal) {
		super();
		this.userId = userId;
		this.organizationId = organizationId;
		this.feedbackVal = feedbackVal;
	}
	


	/**
	 * @return the user_id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the org_id
	 */
	public int getOrganizationId() {
		return organizationId;
	}

	/**
	 * @param org_id the org_id to set
	 */
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * @return the feedbackVal
	 */
	public String getFeedbackVal() {
		return feedbackVal;
	}

	/**
	 * @param feedbackVal the feedbackVal to set
	 */
	public void setFeedbackVal(String feedbackVal) {
		this.feedbackVal = feedbackVal;
	}

	@Override
	public String toString() {
		return "Feedback [user_id=" + userId + ", org_id=" + organizationId + ", feedbackVal=" + feedbackVal + "]";
	}
	
	

}