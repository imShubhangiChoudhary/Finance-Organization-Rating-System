package com.frs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frs.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int id);
}
