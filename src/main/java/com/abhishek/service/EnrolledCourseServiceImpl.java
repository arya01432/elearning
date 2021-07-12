package com.abhishek.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abhishek.repository.EnrolledCourseRepository;
import com.abhishek.model.EnrolledCourse;

@Service
public class EnrolledCourseServiceImpl implements EnrolledCourseService {

	@Autowired
	private EnrolledCourseRepository enrolledCourseRepository;


	@Override
	public List<EnrolledCourse> getAllEnrolledCourses() {
		return enrolledCourseRepository.findAll();
	}

	@Override
	public void saveEnrolledCourse(EnrolledCourse enrolledCourse) {
		this.enrolledCourseRepository.save(enrolledCourse);
	}

	@Override
	public EnrolledCourse getEnrolledCourseById(long id) {
		Optional<EnrolledCourse> optional = enrolledCourseRepository.findById(id);
		EnrolledCourse enrolledCourse = null;
		if (optional.isPresent()) {
			enrolledCourse = optional.get();
		} else {
			throw new RuntimeException(" EnrolledCourse not found for id :: " + id);
		}
		return enrolledCourse;
	}

	@Override
	public void deleteEnrolledCourseById(long id) {
		this.enrolledCourseRepository.deleteById(id);
	}

}
