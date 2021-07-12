package com.abhishek.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhishek.model.Admin;
import com.abhishek.model.Course;
import com.abhishek.model.EnrolledCourse;
import com.abhishek.model.User;
import com.abhishek.service.UserService;
import com.abhishek.service.AdminService;
import com.abhishek.service.CourseService;
import com.abhishek.service.EnrolledCourseService;



@Controller
public class CourseController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private CourseService courseService;

    @Autowired
    private EnrolledCourseService enrolledCourseService;




	@GetMapping("/addCourse/{aid}")
	public String addCourse(@PathVariable(value = "aid") long aid, Model model){
		// Admin admin = adminService.getAdminById(id);

        Course course = new Course();
        model.addAttribute("course",course);
        model.addAttribute("adminLogin",("Welcome "+adminService.getAdminById(aid).getName()));
		model.addAttribute("adminId", aid);
		return "course_form";
	}

	@PostMapping("/saveCourse/{aid}")
	public String saveCourse(@PathVariable(value = "aid") long aid, @ModelAttribute("course") Course course, Model model) {
		// save User to database
		courseService.saveCourse(course);
		Admin admin = adminService.getAdminById(aid);

		model.addAttribute("adminLogin",("Welcome "+ admin.getName()));
		model.addAttribute("adminId",admin.getId());

		return "success_login_admin";
	}

    @GetMapping("/enrollCourse/{id}")
	public String enrollCourse(@PathVariable(value = "id") long id, Model model){
	
        model.addAttribute("userId",id);
        model.addAttribute("userLogin",("Welcome "+ userService.getUserById(id).getName()));
		model.addAttribute("listCourses",courseService.getAllCourses());
		return "course_enroll";
	}

    @GetMapping("/enrolledCourse/{uid}")
	public String enrolledCourse(@PathVariable(value = "uid") long uid, Model model){
	
        List<EnrolledCourse> enrolledCoursesList = new ArrayList<>();
        for (EnrolledCourse enrolledCourse2 : enrolledCourseService.getAllEnrolledCourses()) {
            if (enrolledCourse2.getUserId()==uid) {
                enrolledCoursesList.add(enrolledCourse2);
            } 
        }

        model.addAttribute("userId",uid);
        model.addAttribute("userLogin",("Welcome "+ userService.getUserById(uid).getName()));
        model.addAttribute("listEnrolledCourses",enrolledCoursesList);
		return "course_enrolled";
	}

    @GetMapping("/enrolledCourse/{uid}/{cid}")
	public String enrolledCourse(@PathVariable(value = "uid") long uid, @PathVariable(value = "cid") long cid, Model model){
	

        boolean flag = false;

        for (EnrolledCourse enrolledCourse1 : enrolledCourseService.getAllEnrolledCourses()) {
            if (enrolledCourse1.getCourseId()==cid&& enrolledCourse1.getUserId()==uid) {
                flag = true;
                break;
            } 
        }
        if(!flag){
            EnrolledCourse enrolledCourse = new EnrolledCourse();
        Course course = courseService.getCourseById(cid);
        enrolledCourse.setUserId(uid);
        enrolledCourse.setCourseId(cid);
        enrolledCourse.setCourseName(course.getCourseName());
        enrolledCourse.setCourseDesc(course.getCourseDesc());
        enrolledCourse.setCourseFee(course.getCourseFee());
        enrolledCourse.setCourseRes(course.getCourseRes());
        enrolledCourseService.saveEnrolledCourse(enrolledCourse);

        }
        List<EnrolledCourse> enrolledCoursesList = new ArrayList<>();
        for (EnrolledCourse enrolledCourse2 : enrolledCourseService.getAllEnrolledCourses()) {
            if (enrolledCourse2.getUserId()==uid) {
                enrolledCoursesList.add(enrolledCourse2);
            } 
        }

        model.addAttribute("userLogin",("Welcome "+ userService.getUserById(uid).getName()));
        model.addAttribute("userId",uid);
        model.addAttribute("listEnrolledCourses",enrolledCoursesList);
		return "course_enrolled";
    }
       
           
}
