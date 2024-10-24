package com.example.myapp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myapp2.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
