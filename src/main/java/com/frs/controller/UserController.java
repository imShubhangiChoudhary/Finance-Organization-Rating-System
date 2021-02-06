package com.frs.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.frs.exception.NotFoundException;
import com.frs.exception.OrgExceptionMessage;
import com.frs.model.Feedback;
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

@Api(value = "Controller", description = "Handler methods related to User!!!!")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Internal server error"),
		@ApiResponse(code = 404, message = "Not found") })

@RestController
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
		String userName = user.getFirstName().substring(0, 2) + user.getLastName().substring(0, 2)
				+ userInfo.getUserId();
		Login login = new Login(userName, user.getPassword(), 'U', user.getUserId());
		Login resultLogin = service.addLogin(login);
		logger.info("New user added successfully with id " + userInfo.getUserId());
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
			logger.warn(OrgExceptionMessage.MESSAGETOUSER + " with given rating " + orgRating);
			throw new NotFoundException("No organization found with given rating: " + orgRating);

		}
		logger.info(OrgExceptionMessage.SEARCH + " rating " + orgRating);
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
			logger.warn(OrgExceptionMessage.MESSAGE + orgId);
			throw new NotFoundException(OrgExceptionMessage.MESSAGE + orgId);
		}
		logger.info(OrgExceptionMessage.SEARCH + " id " + orgId);
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
			logger.warn(OrgExceptionMessage.MESSAGETOUSER + " with given name " + orgName);
			throw new NotFoundException("No organization found with given name: " + orgName);
		}
		logger.info(OrgExceptionMessage.SEARCH + " name " + orgName);
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

	@ApiOperation(value = "To add feedback to organization by user")
	@PostMapping("/addFeedback/{userId}/{orgId}")
	public Feedback addFeedback(@PathVariable int userId, @PathVariable int orgId, @RequestBody Feedback feedback) {

		Login login = service.getLogin(orgId);
		Login login1 = service.getLogin(userId);
		if (login != null && login1 != null) {
			if ((login.getEntityType()) == 'O' && (login1.getEntityType()) == 'U') {
//			  Feedback feedbacks = feedbackService.addFeedback(feedback);
//			  return feedbacks;
				logger.info("Feedback added to organization with id " + orgId + " by user with id " + userId);
				return feedbackService.addFeedback(feedback);

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
	public ResponseEntity<User> updatePassword(@PathVariable("id") int id, @RequestBody User user) {
		User usr = service.getUser(id);
		Login login = service.getLogin(id);
		if (usr == null) {
			logger.warn("No User present with the given id: " + id);
			throw new NotFoundException("No User present with the given id: " + id);
		} else {
			usr.setPassword(user.getPassword());
			login.setPassword(user.getPassword());
		}
		User userPassword = service.updatePassword(usr);
		service.updatePassword(login);
		logger.info("Password of user with id " + id + " updated successfully");
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
			logger.warn("No feedback is given by user with id: " + userId);
			throw new NotFoundException("No feedback is given by user with id: " + userId);
		}
		logger.info("Feedbacks displayed successfully to user");
		return new ResponseEntity<>(feedbacks, HttpStatus.OK);

	}
//		@GetMapping("/userLogOut")
//		public String LogOut(){
//			return "Thank you!";
//		}

}

/*
 * @GetMapping("/searchByOrgId/{orgId}") public
 * ResponseEntity<Map<Integer,Organization>> searchById(@PathVariable int
 * orgId){ Organization organizationId = service.getCompanyById(orgId);
 * Map<Integer,Organization> map = null;
 * 
 * if(organizationId == null) { throw new
 * NotFoundException("No organization found with given id:" +orgId); }
 * //Organization org = map.get(organizationId);
 * 
 * map.put(organizationId.getOrgId(), organizationId);
 * 
 * return ResponseEntity<Map<Integer,Organization>>(map.values()); }
 */
