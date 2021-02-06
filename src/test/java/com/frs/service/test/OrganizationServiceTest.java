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

import com.frs.model.Login;
import com.frs.model.Organization;
import com.frs.repository.LoginRepository;
import com.frs.repository.OrganizationRepository;
import com.frs.service.IOrganizationService;

@SpringBootTest
public class OrganizationServiceTest {

	@Autowired
	IOrganizationService service;

	@MockBean
	OrganizationRepository orgRepository;

	@MockBean
	LoginRepository loginRepository;

	@Test
	public void testAddOrganization() {

		Organization organization = new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+", "Approved");

		when(orgRepository.save(organization)).thenReturn(organization);
		assertEquals(organization, service.addOrganization(organization));
	}

	@Test
	public void testGetOrganization() {
		List<Organization> listOrg = Stream.of(
				new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 2, "international",
						45678, "A+", "Approved"),
				new Organization(3, "HDFC", "A", "Private corporation", "hdfcbank8@gmail.com", 3, "national", 98678,
						"A", "Approved"))
				.collect(Collectors.toList());

		orgRepository.saveAll(listOrg);

		when(orgRepository.findById(2)).thenReturn(listOrg.get(0));
		assertEquals(listOrg.get(0), service.getOrganization(2));
	}

	@Test
	public void testAddLogin() {

		Login login = new Login("HDF", "1234", 'O', 3);

		when(loginRepository.save(login)).thenReturn(login);
		assertEquals(login, service.addLogin(login));
	}

	@Test
	public void testRemove() {
	List<Organization> listOrg=Stream.of(
			new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 2, "international",
					45678, "A+", "Approved"),
			new Organization(3, "HDFC", "A", "Private corporation", "hdfcbank8@gmail.com", 3, "national", 98678,
					"A", "Approved"))
			.collect(Collectors.toList());
	
	List<Login> listLogin=Stream.of(
			new Login("HDF","1234",'O',3),
			new Login("SBI","1234",'O',4))
			.collect(Collectors.toList());
	
	orgRepository.saveAll(listOrg);
	loginRepository.saveAll(listLogin);
	
	when(orgRepository.save(listOrg.get(1))).thenReturn(listOrg.get(1));
	assertEquals(listOrg.get(1), service.remove(listOrg.get(1)));
	}

	@Test
	public void testUpdateInterestRate() {

		Organization organization = new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+", "Approved");
		when(orgRepository.save(organization)).thenReturn(organization);
		assertEquals(organization, service.updateInterestRate(organization));
	}
	
	@Test
	public void testUpdateScope() {

		Organization organization = new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+", "Approved");
		when(orgRepository.save(organization)).thenReturn(organization);
		assertEquals(organization, service.updateInterestRate(organization));
	}
	
	@Test
	public void testUpdateGrossIncome() {

		Organization organization = new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+", "Approved");
		when(orgRepository.save(organization)).thenReturn(organization);
		assertEquals(organization, service.updateInterestRate(organization));
	}

	@Test
	public void testupdateScopeFail() {

		Organization organization = new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 0,
				"international", 45678, "A+", "Approved");
		when(orgRepository.save(organization)).thenReturn(organization);
		assertEquals(organization, service.updateInterestRate(organization));
	}

	@Test
	public void testupdateGrossIncomeFail() {

		Organization organization = new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 0,
				"international", 45678, "A+", "Approved");
		when(orgRepository.save(organization)).thenReturn(organization);
		assertEquals(organization, service.updateInterestRate(organization));
	}

	@Test
	public void testUpdateInterestRateFail() {

		Organization organization = new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 0,
				"international", 45678, "A+", "Approved");
		when(orgRepository.save(organization)).thenReturn(organization);
		assertEquals(organization, service.updateInterestRate(organization));
	}

	@Test
	public void testAddOrganizationFail() {

		Organization organization = new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+", "Approved");
		when(orgRepository.save(organization)).thenReturn(organization);
		Organization organization1 = new Organization(2, "Cap", "A", "IT compny", "capgemini662@gmail.com", 1,
				"national", 47000, "A", "UnApproved");

		when(orgRepository.save(organization)).thenReturn(organization1);
		assertNotEquals(organization, organization1);
	}

	@Test
	public void testGetOrganizationFail() {	
		List<Organization> listOrg = Stream.of(
				new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 2, "international",
						45678, "A+", "Approved"),
				new Organization(3, "HDFC", "A", "Private corporation", "hdfcbank8@gmail.com", 3, "national", 98678,
						"A", "Approved"))
				.collect(Collectors.toList());

		orgRepository.saveAll(listOrg);
		Mockito.when(orgRepository.findById(4)).thenReturn(null);
		assertEquals(null, service.getOrganization(4));
	}

	@Test
	public void testAddLoginFail() {	
		
		Login login = null;
		when(loginRepository.save(login)).thenReturn(null);
		assertEquals(login, service.addLogin(login));
	}
	
	public void testRemoveException() {
		List<Organization> listOrg=Stream.of(
				new Organization(2, "Capgemini", "A+", "IT consultancy", "capgemini2@gmail.com", 2, "international",
						45678, "A+", "Approved"),
				new Organization(3, "HDFC", "A", "Private corporation", "hdfcbank8@gmail.com", 3, "national", 98678,
						"A", "Approved"))
				.collect(Collectors.toList());
		
		List<Login> listLogin=Stream.of(
				new Login("HDF","1234",'O',3),
				new Login("SBI","1234",'O',4))
				.collect(Collectors.toList());
		
		orgRepository.saveAll(listOrg);
		loginRepository.saveAll(listLogin);
		
		when(orgRepository.save(listOrg.get(1))).thenReturn(listOrg.get(1));
		assertNotEquals(listOrg.get(1), service.remove(listOrg.get(1)));
		}
}
