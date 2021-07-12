package com.abhishek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}