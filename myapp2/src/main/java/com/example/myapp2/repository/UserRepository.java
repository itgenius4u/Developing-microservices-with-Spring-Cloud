package com.example.myapp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myapp2.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
