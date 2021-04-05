package com.frs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frs.model.Organization;

@Repository
public interface IOrganizationRepository extends JpaRepository<Organization, Integer>{

	List<Organization> findAllByOrgRating(String orgRating);

	Organization findById(int orgId);

	Organization findAllByOrgName(String orgName);

	Object findAllByOrgId(int orgId);
}
