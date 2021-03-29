package com.exam.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.client.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
