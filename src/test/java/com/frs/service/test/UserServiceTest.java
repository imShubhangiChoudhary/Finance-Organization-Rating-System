package com.frs.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.frs.model.Login;
import com.frs.model.Organization;
import com.frs.model.User;
import com.frs.repository.ILoginRepository;
import com.frs.repository.IOrganizationRepository;
import com.frs.repository.IUserRepository;
import com.frs.service.IUserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	IUserService service;

	@MockBean
	IOrganizationRepository orgRepositary;

	@MockBean
	IUserRepository userRepository;

	@MockBean
	ILoginRepository loginrepository;

	@Test
	void testAddUser() {

		User u = new User(15, "Pooja", "Vaidya", "Pooja@123", "pooja28@gmail.com", "9876543223", "Mumbai");
		when(userRepository.save(u)).thenReturn(u);
		assertEquals(u, service.addUser(u));
	}

	@Test
	void testAddLogin() {
		// userName, "1234", 'U', user.getUserId()

		Login login = new Login("shch", "1234", 'U', 23);
		when(loginrepository.save(login)).thenReturn(login);
		assertEquals(login, service.addLogin(login));
	}

	@Test
	void testGetCompanyByRating() {
		String orgRating = "A+";
		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setOrgId(2);
		organization.setStatus("Approved");
		organization.setOrgRating("A+");

		when(orgRepositary.findAllByOrgRating(orgRating))
				.thenReturn(Stream.of(organization).collect(Collectors.toList()));
		assertEquals(1, service.getAllCompany(orgRating).size());

	}

	@Test
	void testGetCompanyByName() {
		String orgName = "Capgemini";

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

		orgRepositary.saveAll(listOrg);

		when(orgRepositary.findAllByOrgName(orgName)).thenReturn(listOrg.get(0));
		assertEquals(listOrg.get(0), service.getCompanyByName(orgName));
	}

	@Test
	void testGetCompanyById() {
		int orgId = 2;
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

		orgRepositary.saveAll(listOrg);

		when(orgRepositary.findById(orgId)).thenReturn(listOrg.get(1));
		assertEquals(listOrg.get(1), service.getCompanyById(orgId));
	}

	@Test
	void testAddLoginFail() {

		Login login = new Login(" ", "1234", 'U', 23);
		when(loginrepository.save(login)).thenReturn(login);
		assertEquals(login, service.addLogin(login));
	}

	@Test
	void testAddUserFail() {
		User u = new User(15, "Pooja", "Vaidya", "Pooja@123", "pooja28@gmail.com", "9876543223", "Mumbai");
		when(userRepository.save(u)).thenReturn(u);
		User u1 = new User(16, "Riya", "Vaidya", "Riya@123", "riya28@gmail.com", "8976543223", "Pune");
		when(userRepository.save(u)).thenReturn(u1);
		assertNotEquals(u, u1);
	}

	@Test
	void testGetCompanyByNameFail() {
		String orgName = "Capgemini";

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

		orgRepositary.saveAll(listOrg);

		when(orgRepositary.findAllByOrgName(orgName)).thenReturn(listOrg.get(0));
		assertEquals(listOrg.get(0), service.getCompanyByName(orgName));
	}

	@Test
	void testGetCompanyByRatingFail() {
		String orgRating = "A+";
		Organization organization = new Organization("Capgemini", "IT consultancy", "capgemini2@gmail.com", 2,
				"international", 45678, "A+");
		organization.setOrgId(2);
		organization.setStatus("Approved");
		organization.setOrgRating("A+");

		when(orgRepositary.findAllByOrgRating(orgRating))
				.thenReturn(Stream.of(organization).collect(Collectors.toList()));
		assertEquals(1, service.getAllCompany(orgRating).size());

	}

	@Test
	void testGetCompanyByIdFail() {
		int orgId = 2;
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

		orgRepositary.saveAll(listOrg);

		when(orgRepositary.findById(orgId)).thenReturn(null);
		assertEquals(null, service.getCompanyById(orgId));
	}

}
