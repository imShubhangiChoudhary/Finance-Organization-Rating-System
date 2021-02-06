package com.frs.service;

import com.frs.model.Login;
import com.frs.model.Organization;

public interface IOrganizationService {

	Organization addOrganization(Organization organization);

	
	Organization getOrganization(int id);


	Login addLogin(Login login);


	Organization updateInterestRate(Organization organization);


	Organization updateScope(Organization organization);


	Organization updateGrossIncome(Organization organization);


	Organization remove(Organization org);


	Login getLogin(int id);


	Login updatePassword(Login logins);

}
