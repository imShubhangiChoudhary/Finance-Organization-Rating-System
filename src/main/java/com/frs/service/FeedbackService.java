package com.frs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frs.model.Feedback;
import com.frs.repository.IFeedbackRepository;

/**
 * Use to view and add feedbacks
 * 
 * @author G4
 * @version
 * @since 2020-2-5
 *
 */

@Service
@Transactional
public class FeedbackService implements IFeedbackService {
	@Autowired
	IFeedbackRepository feedbackRepository;

	/**
	 * This method is use to view feedback of user
	 * 
	 * @param userId
	 * @return List<Feedback>
	 */

	@Override
	public List<Feedback> viewFeedback(int userId) {

		return feedbackRepository.findAllByUserId(userId);
	}

	/**
	 * This method is use by to view feedback for organization
	 * 
	 * @param orgId
	 * @return List<Feedback>
	 */

	@Override
	public List<Feedback> viewFeedbackForOrg(int orgId) {

		return feedbackRepository.findAllByOrganizationId(orgId);
	}

	/**
	 * This method is use by user to add feedback
	 * 
	 * @param feedback
	 * @return Feedback
	 */

	@Override
	public Feedback addFeedback(Feedback feedback) {

		return feedbackRepository.save(feedback);
	}

	@Override
	public List<Feedback> getAllFeedback() {
		
		return feedbackRepository.findAll();
	}

}
