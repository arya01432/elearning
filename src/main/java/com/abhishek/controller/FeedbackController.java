package com.abhishek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.abhishek.model.Feedback;
import com.abhishek.model.User;
import com.abhishek.model.Admin;
import com.abhishek.service.UserService;
import com.abhishek.service.AdminService;
import com.abhishek.service.FeedbackService;


@Controller
public class FeedbackController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;


	@Autowired
	private FeedbackService feedbackService;

	@GetMapping("/feedback/{id}")
	public String feedback(@PathVariable(value = "id") long id, Model model){
		User user = userService.getUserById(id);
		model.addAttribute("user",user);
		Feedback feedback = new Feedback();
		feedback.setUserId(user.getId());

		model.addAttribute("userId",id);
		model.addAttribute("userLogin",("Welcome "+ user.getName()));
		model.addAttribute("feedback",feedback);
		return "feedback_form";
	}

	@PostMapping("/saveFeedback")
	public String saveFeedback(@ModelAttribute("feedback") Feedback feedback, Model model) {
		// save User to database
		feedbackService.saveFeedback(feedback);
		model.addAttribute("userLogin",("Welcome "+ feedback.getName()));
		model.addAttribute("userId",feedback.getUserId());

		return "success_login_user";
	}

	@GetMapping("/feedbackView/{id}")
	public String feedbackView(@PathVariable(value = "id") long id, Model model){
		model.addAttribute("listFeedbacks",feedbackService.getAllFeedbacks());
		model.addAttribute("adminLogin",("Welcome "+adminService.getAdminById(id).getName()));
		model.addAttribute("adminId",id);
		return "feedback_view";
	}
	
}
