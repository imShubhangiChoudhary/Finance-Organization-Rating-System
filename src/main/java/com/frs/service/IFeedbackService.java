package com.frs.service;

import java.util.List;

import com.frs.model.Feedback;

public interface IFeedbackService {
	

	  List<Feedback> viewFeedback(int userId);
	  
	  Feedback addFeedback(Feedback feedback);

	List<Feedback> viewFeedbackForOrg(int orgId);
	 
}
