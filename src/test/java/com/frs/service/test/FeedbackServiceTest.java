package com.frs.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.frs.model.Feedback;
import com.frs.repository.FeedbackRepository;
import com.frs.service.IFeedbackService;


@SpringBootTest
public class FeedbackServiceTest {

	@Autowired

	IFeedbackService feedbackService;

	@MockBean
	FeedbackRepository feedbackRepository;

	@Test
	public void testViewFeedback() {
		// userId, int organizationId, String feedbackVal

		List<Feedback> listFeedback = Stream.of(new Feedback(10,1, 2, "Good"), 
						new Feedback(11,1, 3, "Excellent"),
						new Feedback(12,4, 5, "Bad"))
				.collect(Collectors.toList());

		feedbackRepository.saveAll(listFeedback);

		int userId = 1;
		when(feedbackRepository.findAllByUserId(userId)).thenReturn(
				Stream.of(listFeedback.get(0),listFeedback.get(1)).collect(Collectors.toList()));
		assertEquals(2, feedbackService.viewFeedback(1).size());
	}

	@Test
	public void testAddFeedback() {
		Feedback feedback = new Feedback(3, 2, "Nice!!!");

		when(feedbackRepository.save(feedback)).thenReturn(feedback);
		assertEquals(feedback,feedbackService.addFeedback(feedback));
	}
	@Test
	public void testViewFeedbackException() {
		List<Feedback> feedback=Stream.of(new Feedback(1,9,"Awesome"),new Feedback(1,30,"Good")).collect(Collectors.toList());
		Mockito.when(feedbackService.viewFeedback(1)).thenReturn(feedback);
		assertNotEquals(feedback, feedbackService.viewFeedback(0));
	}
	
	
	  @Test
	  public void testAddFeedbackException() { 
		  Feedback feedback=new Feedback(1,9,"");
		  Feedback feedback1 =new Feedback(1,30,"Nice!!");
		  when(feedbackRepository.save(feedback)).thenReturn(feedback);
		  assertNotEquals(feedback1, feedbackService.addFeedback(feedback));
		  
	  
	  }
}
