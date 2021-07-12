package com.abhishek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.model.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}