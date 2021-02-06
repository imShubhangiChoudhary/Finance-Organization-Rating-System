package com.frs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frs.model.Organization;
import com.frs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int id);

//	List<Organization> findAllByOrgRating(String orgRating);
//
//	Organization findAllByOrgId(int orgId);
//
//	Organization findAllByOrgName(String orgName);

}
