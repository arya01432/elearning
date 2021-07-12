package com.abhishek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.model.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}