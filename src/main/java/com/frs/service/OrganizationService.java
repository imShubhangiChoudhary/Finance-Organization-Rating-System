package com.frs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frs.model.Login;
import com.frs.model.Organization;
import com.frs.repository.LoginRepository;
import com.frs.repository.OrganizationRepository;

/**
 * This class performs operations related to organization
 * and interact with organization and login repository
 * 
 * @author G4
 * @version 
 * @since 2020-2-5
 *
 */

@Service
@Transactional
public class OrganizationService implements IOrganizationService{

	@Autowired
	OrganizationRepository repository;
	@Autowired
	LoginRepository loginrepository;
	
	/**
	 * 
	 * This method adds new organization record to our repository
	 * and return that record
	 * @param organization
	 * @return Organization
	 * 
	 */
	
	@Override
	public Organization addOrganization(Organization organization) {
		repository.save(organization);
		return organization;
	}
	
	/**
	 * 
	 * This method return the record from our organization repository based on
	 * based on given id
	 * @param id
	 * @return Organization
	 * 
	 */
	
	@Override
	public Organization getOrganization(int id) {
			return repository.findById(id);
		}

	/**
	 * 
	 * This method adds new login record to our repository
	 * and return that record
	 * @param login
	 * @return Login
	 * 
	 */
	
	@Override
	public Login addLogin(Login login) {
		loginrepository.save(login);
		return login;
	}
	
	/**
	 * 
	 * This method updates organization's interest rate in our repository
	 * and return that updated record
	 * @param organization
	 * @return Organization
	 * 
	 */
	
	@Override
	public Organization updateInterestRate(Organization organization) {
	return repository.save(organization);
	}

	/**
	 * 
	 * This method updates organization's scope in our repository
	 * and return that updated record
	 * @param organization
	 * @return Organization
	 * 
	 */
	
	@Override
	public Organization updateScope(Organization organization) {
	return repository.save(organization);
	}

	/**
	 * 
	 * This method updates organization's gross income in our repository
	 * and return that updated record
	 * @param Organization
	 * @return Organization
	 * 
	 */
	
	@Override
	public Organization updateGrossIncome(Organization organization) {
	return repository.save(organization);
	}
	
	/**
	 * 
	 * This method removes  the organization from our login repository
	 * and sets that record's status as deleted in organization repository
	 * @param org
	 * @return Organization
	 * 
	 */
	
	@Override
	public Organization remove(Organization org) {
		loginrepository.deleteById(org.getOrgId());
		return repository.save(org);
		
	}
 
	/**
	 * 
	 * This method return the record from our login repository based on
	 * based on given id
	 * @param id
	 * @return Login
	 * 
	 */
	
	@Override
	public Login getLogin(int id) {
	return loginrepository.findById(id);
	}

	/**
	 * 
	 * This method update the password of organizations 
	 * @param logins
	 * @return Login
	 * 
	 */
	
	@Override
	public Login updatePassword(Login logins) {
	return loginrepository.save(logins);
	}
}
