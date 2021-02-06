package com.frs.service;

import java.util.List;

import com.frs.model.Login;
import com.frs.model.Organization;
import com.frs.model.User;

public interface IUserService {

	User addUser(User user);

	List<Organization> getAllCompany(String orgRating);

	Organization getCompanyById(int orgId);

	Organization getCompanyByName(String orgName);

	Login addLogin(Login login);

	Login getLogin(int id);

	Login updatePassword(Login login);

	User updatePassword(User user);

	User getUser(int id);
}
