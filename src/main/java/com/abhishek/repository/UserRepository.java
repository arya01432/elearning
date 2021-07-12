package com.abhishek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}