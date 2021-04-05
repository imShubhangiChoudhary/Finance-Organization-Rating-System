package com.frs.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frs.exception.NotFoundException;
import com.frs.exception.OrgExceptionMessage;
import com.frs.model.Feedback;
import com.frs.model.FinAppRequest;
import com.frs.model.Login;
import com.frs.model.Organization;
import com.frs.model.User;
import com.frs.service.FeedbackService;
import com.frs.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * User Controller
 * 
 * @author G4
 * @version
 * @since 2020-2-5
 *
 */

@Api(value = "Controller")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
		@ApiResponse(code = 404, message = "Not found") })

@RestController
@RequestMapping(path = "api")
@CrossOrigin
public class UserController {

	@Autowired
	IUserService service;

	@Autowired
	FeedbackService feedbackService;
	
	/**
	 * This method is used to register user
	 * 
	 * @param user
	 * @return id of the user
	 */
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "To register new User", response = User.class)
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
		
		User userInfo = service.addUser(user);
		
		String userName=user.getFirstName().substring(0, 2)+
				user.getLastName().substring(0, 2)+user.getUserId();
		
		Login login = new Login(userName, user.getPassword(), 'U', user.getUserId());
		Login resultLogin = service.addLogin(login);
		logger.info("New user added successfully");
		return new ResponseEntity<>("New user added with id " + userInfo.getUserId() + ". Your username is "
				+ resultLogin.getUserName() + " and password is " + resultLogin.getPassword(), HttpStatus.OK);
	}

	/**
	 * This method is use to search organization by it's rating
	 * 
	 * @param orgRating
	 * @return List<Organization>
	 */

	@ApiOperation(value = "Search organizations by rating")
	@GetMapping("/searchByRating/{orgRating}")
	public ResponseEntity<List<Organization>> searchByRating(@PathVariable String orgRating) {

		List<Organization> rating = service.getAllCompany(orgRating);
		if (rating.isEmpty()) {
			logger.warn("No organization present with given rating"  );
			throw new NotFoundException("No organization found with given rating "+ orgRating);

		}
		logger.info("Display the Organization name with given rating ");
		return new ResponseEntity<>(rating, HttpStatus.OK);

	}

	/**
	 * This method is use to get organization by its id
	 * 
	 * @param orgId
	 * @return Organization
	 */

	@ApiOperation(value = "Search organization by id")
	@GetMapping("/searchByOrgId/{orgId}")
	public ResponseEntity<Organization> searchById(@PathVariable int orgId) {
		Organization organizationId = service.getCompanyById(orgId);
		if (organizationId == null) {
			logger.warn("No organization present with given id");
			throw new NotFoundException(OrgExceptionMessage.MESSAGE + orgId);
		}
		logger.info(OrgExceptionMessage.SEARCH + " id  " );
		return new ResponseEntity<>(organizationId, HttpStatus.OK);

	}

	/**
	 * This method is use to get organization by its name
	 * 
	 * @param orgName
	 * @return Organization
	 */

	@ApiOperation(value = "Search organizations by name")
	@GetMapping("/searchByOrgName/{orgName}")
	public ResponseEntity<Organization> searchByOrgName(@PathVariable String orgName) {
		Organization organizationName = service.getCompanyByName(orgName);
		if (organizationName == null) {
			logger.warn("No Organization present with given name");
			throw new NotFoundException("No organization found with given name "+orgName);
		}
		logger.info(OrgExceptionMessage.SEARCH  );
		return new ResponseEntity<>(organizationName, HttpStatus.OK);

	}

	/**
	 * This method is use to give feedback to organization
	 * 
	 * @param userId
	 * @param orgId
	 * @param feedback
	 * @return String
	 */

//	@ApiOperation(value = "To add feedback to organization by user")
//	@PostMapping("/addFeedback/{userId}/{orgId}")
//	public Feedback addFeedback(@PathVariable int userId, @PathVariable int orgId, @RequestBody Feedback feedback) {
//
//		Login login = service.getLogin(orgId);
//		Login login1 = service.getLogin(userId);
//		if (login != null && login1 != null) {
//			if ((login.getEntityType()) == 'O' && (login1.getEntityType()) == 'U') {
//				logger.info("Feedback added to organization Succssfully!! ");
//				return feedbackService.addFeedback(feedback);
//
//			} else {
//				logger.warn(" organization or user type is not matching");
//				throw new NotFoundException(" organization or user type is not matching");
//			}
//		} else {
//			logger.warn("No user or organization present ");
//			throw new NotFoundException("No user or organization present ");
//		}
//	}
	
	// http://localhost:8080/api/addFeedback/36/28
		@ApiOperation(value = "To add feedback to organization by user")
		@PostMapping("/addFeedback")
		public ResponseEntity<String> addFeedback(@RequestBody Feedback feedback) {
					Login login = service.getLogin(feedback.getOrganizationId());
					Login login1 = service.getLogin(feedback.getUserId());
					if (login != null && login1 != null) {
						if ((login.getEntityType()) == 'O' && (login1.getEntityType()) == 'U') {
							logger.info("Feedback added to organization Succssfully!! ");
						     feedback = feedbackService.addFeedback(feedback);
							return new ResponseEntity<>("Feedback added to organization id" +feedback.getOrganizationId(), HttpStatus.OK);

						} else {
							logger.warn(" organization or user type is not matching");
							throw new NotFoundException(" organization or user type is not matching");
						}
					} else {
						logger.warn("No user or organization present ");
						throw new NotFoundException("No user or organization present ");
					}
				}

	/**
	 * This method updates or change password of user
	 * 
	 * @param id
	 * @param user
	 * @return updated password with given id
	 */

	@ApiOperation(value = "To update the user's password")
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<User> updatePassword(@PathVariable("id") int id, @RequestBody FinAppRequest finAppRequest) {
		User usr = service.getUser(id);
		Login login = service.getLogin(id);
		if (usr == null) {
			logger.warn("No User present with the given id: ");
			throw new NotFoundException("No User present with the given id: " +id);
		} else {
			usr.setPassword(finAppRequest.getUser().getPassword());
			login.setPassword(finAppRequest.getUser().getPassword());
		}
		User userPassword = service.updatePassword(usr);
		service.updatePassword(login);
		logger.info("Password of user updated successfully ");
		return new ResponseEntity<>(userPassword, HttpStatus.OK);
	}
	
	/**
	 * This method is use to view feedback
	 * 
	 * @param userId
	 * @return feedback given by user
	 */

	@ApiOperation(value = "To view all feedbacks given to organization by organization's id")
	@GetMapping("/ViewFeedback/{userId}")
	public ResponseEntity<List<Feedback>> viewFeedback(@PathVariable int userId) {
		List<Feedback> feedbacks = feedbackService.viewFeedback(userId);
		if (feedbacks.isEmpty()) {
			logger.warn("No feedback is given by user with id" );
			throw new NotFoundException("No feedback is given by user with id: " );
		}
		logger.info("Feedbacks displayed successfully to user");
		return new ResponseEntity<>(feedbacks, HttpStatus.OK);

	}
	
	@ApiOperation(value = "View feedback", response = Feedback.class)
	@GetMapping("/showAllFeedback")
	public ResponseEntity<List<Feedback>> showAllFeedback() {
		 List<Feedback> list = feedbackService.getAllFeedback();
		 return  new ResponseEntity<>(list, HttpStatus.OK);
		
			
		}
}