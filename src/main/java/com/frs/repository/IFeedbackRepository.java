package com.frs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frs.model.Feedback;

@Repository	//IFee
public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {

	List<Feedback> findAllByUserId(int userId);
	
	List<Feedback> findAllByOrganizationId(int organizationId);
	 

}