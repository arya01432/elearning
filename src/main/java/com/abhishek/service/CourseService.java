package com.abhishek.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abhishek.model.Course;

public interface CourseService {
	List<Course> getAllCourses();
	void saveCourse(Course Course);
	Course getCourseById(long id);
	void deleteCourseById(long id);
	
}
