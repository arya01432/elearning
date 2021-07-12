package com.abhishek.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abhishek.model.EnrolledCourse;

public interface EnrolledCourseService {
	List<EnrolledCourse> getAllEnrolledCourses();
	void saveEnrolledCourse(EnrolledCourse EnrolledCourse);
	EnrolledCourse getEnrolledCourseById(long id);
	void deleteEnrolledCourseById(long id);
	
}
