package com.abhishek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "course_desc")
	private String courseDesc;
	
	@Column(name = "course_res")
	private String courseRes;
	
    @Column(name = "course_fee")
	private Double courseFee;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public String getCourseRes() {
		return courseRes;
	}
	public void setCourseRes(String courseRes) {
		this.courseRes = courseRes;
	}
    public Double getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(Double courseFee) {
		this.courseFee = courseFee;
	}
}
