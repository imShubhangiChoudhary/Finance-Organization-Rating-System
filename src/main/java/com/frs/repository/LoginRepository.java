package com.frs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frs.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String>//because our pk is unm not id
{
	//public Course findByCourseName(String courseName);

	public Login findById(int id);//find by id
	public void deleteById(int id);	//delete record by org_id




}
