package com.abhishek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.model.EnrolledCourse;


@Repository
public interface EnrolledCourseRepository extends JpaRepository<EnrolledCourse, Long>{

}