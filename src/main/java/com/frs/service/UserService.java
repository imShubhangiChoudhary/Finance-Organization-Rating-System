package com.frs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frs.model.Login;
import com.frs.model.Organization;
import com.frs.model.User;
import com.frs.repository.LoginRepository;
import com.frs.repository.OrganizationRepository;
import com.frs.repository.UserRepository;

/**
 * Perform user related activities like Registering,Browsing,Updating
 * 
 * @author G4
 * @version
 * @since 2020-2-5
 * 
 */

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	UserRepository repository;
	@Autowired
	OrganizationRepository orgRepository;
	@Autowired
	LoginRepository loginRepositary;

	/**
	 * This method is used to add or registered new user record to user repository
	 * return user
	 */

	@Override
	public User addUser(User user) {
		repository.save(user);
		return user;

	}

	/**
	 * This method is use to get company by organization rating return
	 * List<Organization>
	 */

	@Override
	public List<Organization> getAllCompany(String orgRating) {

		return orgRepository.findAllByOrgRating(orgRating);
	}

	/**
	 * This method is use to get company by organization id return Organization
	 */

	@Override
	public Organization getCompanyById(int orgId) {

		Organization org = orgRepository.findById(orgId);
		return org;

	}

	/**
	 * This method is use to get company by organization name return Organization
	 */
	@Override
	public Organization getCompanyByName(String orgName) {

		return orgRepository.findAllByOrgName(orgName);
	}

	/**
	 * This method adds new login record to login repository and return that record
	 */

	@Override
	public Login addLogin(Login login) {

		return loginRepositary.save(login);
	}

	/**
	 * This method return the record from login repository based on given id
	 */

	@Override
	public Login getLogin(int id) {
		return loginRepositary.findById(id);
	}

	/**
	 * This method updates password of user
	 */

	@Override
	public Login updatePassword(Login login) {
		return loginRepositary.save(login);
	}

	/**
	 * This method updates password of user
	 */

	@Override
	public User updatePassword(User user) {
		return repository.save(user);
	}

	/**
	 * This method return the record from user repository based on given id
	 */

	@Override
	public User getUser(int id) {
		return repository.findById(id).orElse(null);

	}
}