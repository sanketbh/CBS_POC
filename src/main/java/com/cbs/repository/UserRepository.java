package com.cbs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbs.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);

	public Optional<User> findByUserId(int userId);

}
