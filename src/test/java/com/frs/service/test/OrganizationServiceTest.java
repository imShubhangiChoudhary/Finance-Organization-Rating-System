package com.frs.service.test;

import static org.junit.Assert.assertNull;
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

import com.frs.controller.OrganizationUtility;
import com.frs.model.Login;
import com.frs.model.Organization;
import com.frs.repository.ILoginRepository;
import com.frs.repository.IOrganizationRepository;
import com.frs.service.IOrganizationService;

@SpringBootTest
class OrganizationServiceTest {

	@Autowired
	IOrganizationService service;
	@Autowired
	OrganizationUtility orgUtility;
	@MockBean
	ILoginRepository loginRepository;
	@MockBean
	IOrganizationRepository orgRepository;

	{
		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setOrgId(2);
		organization.setStatus("Approved");
		organization.setOrgRating("A+");

		Organization organization1 = new Organization("HDFC", "Private corporation", "hdfcbank8@gmail.com", 3,
				"national", 98678, "A");
		organization1.setOrgId(3);
		organization1.setStatus("Approved");
		organization1.setOrgRating("A");
		List<Organization> listOrg = Stream.of(organization, organization1).collect(Collectors.toList());

		
	}
	
	@Test
	void testAddOrganization() {

		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setOrgId(2);
		String rating = orgUtility.generateRating(organization.getInterestRate(), organization.getGrossIncome(),
				organization.getScope());
		String status = orgUtility.generateStatus(organization.getOrgUserRating(), rating);
		organization.setStatus(status);
		organization.setOrgRating(rating);
		when(orgRepository.save(organization)).thenReturn(organization);
		assertEquals(organization, service.addOrganization(organization));
	}

	@Test
	void testGetOrganization() {
		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setOrgId(2);
		organization.setStatus("Approved");
		organization.setOrgRating("A+");

		Organization organization1 = new Organization("HDFC", "Private corporation", "hdfcbank8@gmail.com", 3,
				"national", 98678, "A");
		organization1.setOrgId(3);
		organization1.setStatus("Approved");
		organization1.setOrgRating("A");
		List<Organization> listOrg = Stream.of(organization, organization1).collect(Collectors.toList());

		orgRepository.saveAll(listOrg);

		when(orgRepository.findById(2)).thenReturn(listOrg.get(0));
		assertEquals(listOrg.get(0), service.getOrganization(2));
	}

	@Test
	void testAddLogin() {

		Login login = new Login("HDF", "1234", 'O', 3);

		when(loginRepository.save(login)).thenReturn(login);
		assertEquals(login, service.addLogin(login));
	}

	@Test
	void testRemove() {
		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setOrgId(2);
		organization.setStatus("Approved");
		organization.setOrgRating("A+");

		Organization organization1 = new Organization("HDFC", "Private corporation", "hdfcbank8@gmail.com", 3,
				"national", 98678, "A");
		organization1.setOrgId(3);
		organization1.setStatus("Approved");
		organization1.setOrgRating("A");
		List<Organization> listOrg = Stream.of(organization, organization1).collect(Collectors.toList());

		List<Login> listLogin = Stream.of(new Login("HDF", "1234", 'O', 3), new Login("SBI", "1234", 'O', 4))
				.collect(Collectors.toList());

		orgRepository.saveAll(listOrg);
		loginRepository.saveAll(listLogin);

		when(orgRepository.save(listOrg.get(1))).thenReturn(listOrg.get(1));
		assertEquals(listOrg.get(1), service.remove(listOrg.get(1)));
	}

	@Test
	void testUpdateInterestRate() {

		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setStatus("Approved");
		organization.setOrgRating("A+");
		organization.setOrgId(2);
		when(orgRepository.save(organization)).thenReturn(organization);
		assertEquals(organization, service.updateInterestRate(organization));
		assertEquals(organization, service.updateScope(organization));
		assertEquals(organization, service.updateGrossIncome(organization));
		assertEquals(organization, service.updateGrossIncome(organization));
		assertEquals(organization, service.updateInterestRate(organization));
		assertEquals(organization, service.updateScope(organization));
	}

	@Test
	void testAddOrganizationFail() {

		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setOrgId(2);
		organization.setStatus("Approved");
		organization.setOrgRating("A+");
		Organization organization1 = new Organization("Cap", "IT compny", "capgemini662@gmail.com", 1,
				"national", 47000, "A");
		organization1.setOrgId(2);
		organization1.setStatus("UnApproved");
		organization1.setOrgRating("A");
		
		when(orgRepository.save(organization)).thenReturn(organization);

		when(orgRepository.save(organization)).thenReturn(organization1);
		assertNotEquals(organization, organization1);
	}

	@Test
	void testGetOrganizationFail() {
		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setOrgId(2);
		organization.setStatus("Approved");
		organization.setOrgRating("A+");

		Organization organization1 = new Organization("HDFC", "Private corporation", "hdfcbank8@gmail.com", 3,
				"national", 98678, "A");
		organization1.setOrgId(3);
		organization1.setStatus("Approved");
		organization1.setOrgRating("A");
		List<Organization> listOrg = Stream.of(organization, organization1).collect(Collectors.toList());


		orgRepository.saveAll(listOrg);
		Mockito.when(orgRepository.findById(4)).thenReturn(null);
		assertEquals(null, service.getOrganization(4));
	}
	
	@Test
	void getLogin() {
		List<Login> login = Stream.of(new Login("HDF", "1234", 'O', 3),new Login("Cap", "1234", 'O', 6)
				,new Login("Shch10", "1234", 'U', 10),new Login("Dach16", "1234", 'U', 16))
				.collect(Collectors.toList());
		loginRepository.saveAll(login);
		Mockito.when(loginRepository.findById(3)).thenReturn(login.get(0));
		assertEquals(login.get(0), service.getLogin(3));
		
	}
	
	@Test
	void getLoginFail() {
		List<Login> login = Stream.of(new Login("HDF", "1234", 'O', 3),new Login("Cap", "1234", 'O', 6)
				,new Login("Shch10", "1234", 'U', 10),new Login("Dach16", "1234", 'U', 16))
				.collect(Collectors.toList());
		loginRepository.saveAll(login);
		Mockito.when(loginRepository.findById(50)).thenReturn(null);
		assertNull(service.getLogin(50));
		
	}
	
	@Test
	void testAddLoginFail() {

		Login login = null;
		when(loginRepository.save(login)).thenReturn(null);
		assertEquals(login, service.addLogin(login));
	}

	void testRemoveException() {
		
		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setOrgId(2);
		organization.setStatus("Approved");
		organization.setOrgRating("A+");

		Organization organization1 = new Organization("HDFC", "Private corporation", "hdfcbank8@gmail.com", 3,
				"national", 98678, "A");
		organization1.setOrgId(3);
		organization1.setStatus("Approved");
		organization1.setOrgRating("A");
		List<Organization> listOrg = Stream.of(organization, organization1).collect(Collectors.toList());

		List<Login> listLogin = Stream.of(new Login("HDF", "1234", 'O', 3), new Login("SBI", "1234", 'O', 4))
				.collect(Collectors.toList());

		orgRepository.saveAll(listOrg);
		loginRepository.saveAll(listLogin);

		when(orgRepository.save(listOrg.get(1))).thenReturn(listOrg.get(1));
		assertEquals(listOrg.get(1), service.remove(listOrg.get(1)));
	}
}
