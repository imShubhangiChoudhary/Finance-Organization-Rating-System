package com.frs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Main class from where our program starts executing
 * 
 * @author G4
 * @version 
 * @since 2020-2-5
 *
 */

@SpringBootApplication
public class FinanceOrgRatingSystemApplication {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Logger logger=LoggerFactory.getLogger(FinanceOrgRatingSystemApplication.class);
		
		SpringApplication.run(FinanceOrgRatingSystemApplication.class, args);
		logger.info("Application Started");
		System.out.println("***** Welcome to Financial Organization Rating System *****");
				
	}

}
