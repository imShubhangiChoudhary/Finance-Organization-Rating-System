package com.frs.controller;

import java.util.ArrayList;
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
import com.frs.service.FeedbackService;
import com.frs.service.IOrganizationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * OrganizationController layer describe all the function of organization
 * 
 * @author G4
 * @version
 * @since 2020-2-5
 *
 */

@Api(value = "Controller", description = "Handler methods related to Organization!!!!")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Internal server error"),
		@ApiResponse(code = 404, message = "Not found") })
@RestController
public class OrganizationController {

	@Autowired
	IOrganizationService service;
	@Autowired
	OrganizationSetConditions setCondition;
	@Autowired
	FeedbackService feedbackService;
	Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	/**
	 * @addOrganization function is used to register the organization
	 * @param organization
	 * @return organization
	 * 
	 */

	@ApiOperation(value = "To register new Organization", response = Organization.class)
	@PostMapping("/registerOrganization")
	public ResponseEntity<String> addOrganization(@Valid @RequestBody Organization organization) {
		String rating = setCondition.setRating(organization.getInterestRate(), organization.getGrossIncome(),
				organization.getScope());
		organization.setOrgRating(rating);
		String status = setCondition.setStatus(organization.getOrgUserRating(), rating);
		organization.setStatus(status);
		Organization result = service.addOrganization(organization);
		String userName = organization.getOrgName().substring(0, 3) + result.getOrgId();
		Login login = new Login(userName, "1234", 'O', organization.getOrgId());
		Login resultLogin = service.addLogin(login);
		logger.info("New Organization added successfully with id "+result.getOrgId());
		return new ResponseEntity<>("New organization added with id " + result.getOrgId() + ". Your username is "
				+ resultLogin.getUserName() + " and password is " + resultLogin.getPassword(), HttpStatus.OK);
	}

	/**
	 * 
	 * @updateGrossIncome method is used to update the gross income
	 * @param id
	 * @param organization
	 * @return organization
	 */

	@ApiOperation(value = "To update gross income of organization", response = Organization.class)
	@PutMapping("/updateGrossIncome/{id}")
	public ResponseEntity<Organization> updateGrossIncome(@PathVariable("id") int id,
			@RequestBody Organization organization) {
		Organization org = service.getOrganization(id);
		if (org == null) {
			logger.warn(OrgExceptionMessage.MESSAGE + id);
			throw new NotFoundException(OrgExceptionMessage.MESSAGE + id);
		} else {
			org.setGrossIncome(organization.getGrossIncome());

		}
		Organization organizationGrossIncome = service.updateGrossIncome(org);
		logger.info("Gross income of Organization with id " + id + " updated successfully");
		return new ResponseEntity<>(organizationGrossIncome, HttpStatus.OK);
	}

	/**
	 * 
	 * @updateScope method to update the scope of organization
	 * @param id
	 * @param organization
	 * @return organization
	 * 
	 */

	@ApiOperation(value = "To update scope of organization", response = Organization.class)
	@PutMapping("/updateScope/{id}")
	public ResponseEntity<Organization> updateScope(@PathVariable("id") int id,
			@RequestBody Organization organization) {
		Organization org = service.getOrganization(id);
		if (org == null) {
			logger.warn(OrgExceptionMessage.MESSAGE + id);
			throw new NotFoundException(OrgExceptionMessage.MESSAGE + id);
		} else {
			org.setScope(organization.getScope());
		}
		Organization organizationScope = service.updateScope(org);
		logger.info("Scope of Organization with id " + id + " updated successfully");
		
		return new ResponseEntity<>(organizationScope, HttpStatus.OK);
	}

	/**
	 * 
	 * @updateInterestRate method to update the interest rate of organization
	 * @param id
	 * @param organization
	 * @return organization
	 * 
	 */

	@ApiOperation(value = "To update interest rate of organization", response = Organization.class)
	@PutMapping("/updateInterest/{id}")
	public ResponseEntity<Organization> updateInterestRate(@PathVariable("id") int id,
			@RequestBody Organization organization) {
		Organization org = service.getOrganization(id);
		if (org == null) {
			logger.warn(OrgExceptionMessage.MESSAGE + id);
			throw new NotFoundException(OrgExceptionMessage.MESSAGE + id);
		} else {
			org.setInterestRate(organization.getInterestRate());
		}
		Organization organizationInterest = service.updateInterestRate(org);
		logger.info("Interest rate of Organization with id " + id + " updated successfully");
		
		return new ResponseEntity<>(organizationInterest, HttpStatus.OK);

	}

	/**
	 * @removeOrganization method to update the remove organization
	 * @param id
	 * @return organization
	 * 
	 */

	@ApiOperation(value = "To remove organization", response = Organization.class)
	@PutMapping("/removeOrganization/{id}")
	public String removeOrganization(@PathVariable("id") int id) {
		Organization org = service.getOrganization(id);
		if (org == null) {
			logger.warn(OrgExceptionMessage.MESSAGE + id);
			throw new NotFoundException(OrgExceptionMessage.MESSAGE + id);
		} else {
			org.setStatus("Deleted");
		}

		Organization organizationRemove = service.remove(org);
		logger.info("Organization with id " + id + " removed successfully");
		
		return "The organization deleted succesfully with id " + organizationRemove.getOrgId();
	}

	/**
	 * 
	 * @updateOrganizationPassword method to update password of organization
	 * @param id
	 * @param logins
	 * @return Login
	 * 
	 */

	@ApiOperation(value = "To update password of organization", response = Organization.class)
	@PutMapping("/updateOrganizationPassword/{id}")
	public ResponseEntity<Login> updatePassword(@PathVariable("id") int id, @RequestBody Login logins) {
		Login login = service.getLogin(id);
		if (login == null) {
			logger.warn(OrgExceptionMessage.MESSAGE + id);
			throw new NotFoundException(OrgExceptionMessage.MESSAGE + id);
		} else {
			login.setPassword(logins.getPassword());
		}
		Login organizationPassword = service.updatePassword(login);
		logger.info("Password of Organization with id " + id + " updated successfully");
		
		return new ResponseEntity<>(organizationPassword, HttpStatus.OK);

	}

	/**
	 * 
	 * @ViewFeedbackOrg method to View feedback of organization given by users
	 * @param id
	 * @return List<Feedback>
	 * 
	 */

	@ApiOperation(value = "To view all feedbacks given to organization", response = Organization.class)
	@GetMapping("/ViewFeedbackOrg/{id}")
	public ResponseEntity<List<Feedback>> viewFeedback(@PathVariable int id) {

		if (service.getOrganization(id) != null) {

			List<Feedback> feedbacks = feedbackService.viewFeedbackForOrg(id);
			if (feedbacks.isEmpty()) {
				logger.warn("No feedback is given to organization with id " + id);
				throw new NotFoundException("No feedback is given to organization with id: " + id);
			}
			logger.info("Feedback displayed successfully for organization with id "+id);
			
			return new ResponseEntity<>(feedbacks, HttpStatus.OK);
		} else {
			logger.warn(OrgExceptionMessage.MESSAGE + id);
			throw new NotFoundException(OrgExceptionMessage.MESSAGE + id);
		}
	}

	/**
	 * 
	 * @checkRatingCritera method to display rating criteria
	 * @return list
	 * 
	 */

	@ApiOperation(value = "To check rating criteria for organization", response = Organization.class)
	@GetMapping("/checkRatingCritera")
	public List<String> checkRatingCriteria() {
		List<String> list = new ArrayList<>();
		list.add("The Rating criteria is based on:");
		list.add("1.Company Interest rate");
		list.add("2.The Scope of company(National|International|State|Local)");
		list.add("3. Gross Income:");
		logger.info("Rating criteria displayed successfully");
		return list;
	}

}