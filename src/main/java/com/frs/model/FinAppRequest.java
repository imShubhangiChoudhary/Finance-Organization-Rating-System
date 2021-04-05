package com.frs.model;

import javax.validation.Valid;

/**
 * User Controller
 * 
 * @author G4
 * @version
 * @since 2020-2-5
 *
 */

public class FinAppRequest {
	@Valid
	private Organization organization;
	
	@Valid
	private Login login;

	@Valid
	private User user;
	
	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
