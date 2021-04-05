package com.frs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frs.model.Login;

@Repository
public interface ILoginRepository extends JpaRepository<Login, String>// because our pk is unm not id
{
	public Login findById(int id);// find by id

	public void deleteById(int id); // delete record by org_id
	
	public Login findByUserNameAndPassword(String unm, String password);

} 
