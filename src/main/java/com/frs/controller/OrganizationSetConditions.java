package com.frs.controller;

import org.springframework.stereotype.Service;

/**
 * This class sets rating to newly registered organization and sets their status 
 * 
 * @author G4
 * @version 
 * @since 2020-2-5
 *
 */

@Service
public class OrganizationSetConditions {

	/**
	 * This method set rating to newly registered organization
	 * 
	 * @param orgInterestRate
	 * @param orgGrossIncome
	 * @param scope
	 * @return
	 */
	
	public String setRating(double orgInterestRate,double orgGrossIncome,String scope) {
		 
		String rating="";
		if ((orgInterestRate <= 10 || orgInterestRate > 7) && scope.equalsIgnoreCase("International")
				&& orgGrossIncome > 80000)
			rating = "A++";
		else if ((orgInterestRate <= 7 || orgInterestRate > 5) && scope.equalsIgnoreCase("International")
				&& (orgGrossIncome >= 70000 || orgGrossIncome < 80000))
			rating = "A+";
		else if ((orgInterestRate <= 5 || orgInterestRate > 2) && scope.equalsIgnoreCase("International")
				&& (orgGrossIncome < 80000 || orgGrossIncome > 5000))
			rating = "A";
		else if ((orgInterestRate <= 10 || orgInterestRate > 7) && scope.equalsIgnoreCase("national")
				&& orgGrossIncome > 80000)
			rating = "B++";
		else if ((orgInterestRate <= 7 || orgInterestRate > 5) && scope.equalsIgnoreCase("national")
				&& (orgGrossIncome >= 70000 || orgGrossIncome < 80000))
			rating = "B+";
		else if (orgInterestRate <= 5 || orgInterestRate > 2 && scope.equalsIgnoreCase("national")
				&& orgGrossIncome < 80000 && orgGrossIncome > 5000)
			rating = "B";
		else if ((orgInterestRate <= 10 || orgInterestRate > 7) && scope.equalsIgnoreCase("state")
				&& orgGrossIncome > 80000)
			rating = "C++";
		else if ((orgInterestRate <= 7 || orgInterestRate > 5) && scope.equalsIgnoreCase("state")
				&& (orgGrossIncome >= 70000 || orgGrossIncome < 80000))
			rating = "C+";
		else if ((orgInterestRate <= 5 || orgInterestRate > 2) && scope.equalsIgnoreCase("state")
				&& (orgGrossIncome < 80000 || orgGrossIncome > 5000))
			rating = "C";
		else if ((orgInterestRate <= 10 || orgInterestRate > 7) && scope.equalsIgnoreCase("Local")
				&& orgGrossIncome > 80000)
			rating = "C++";
		else if ((orgInterestRate <= 7 || orgInterestRate > 5) && scope.equalsIgnoreCase("Local")
				&& (orgGrossIncome >= 70000 || orgGrossIncome < 80000))
			rating = "C+";
		else if ((orgInterestRate <= 5 || orgInterestRate > 2) && scope.equalsIgnoreCase("Local")
				&& (orgGrossIncome < 80000 || orgGrossIncome > 5000))
			rating = "C";

		else {
			rating = "Not satisfied our criteria";
		}

		return rating;

	}

	/**
	 * This method sets status of organization
	 * 
	 * @param userRating
	 * @param estimatedRating
	 * @return
	 */
	
	public String setStatus(String userRating,String estimatedRating) {
		String status = "";
		
		if (userRating.equals(estimatedRating)) {
			status = "Approved";
		} else {
			status = "UnApproved";
		}
		return status;
	}
}
