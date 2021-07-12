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

// import com.abhishek.model.Contact;
// import com.abhishek.model.Feedback;
import com.abhishek.model.User;
import com.abhishek.service.UserService;
// import com.abhishek.service.ContactService;
// import com.abhishek.service.FeedbackService;


@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// @Autowired
	// private ContactService contactService;

	// @Autowired
	// private FeedbackService feedbackService;


	@GetMapping("/error")
	public String handleError(){
		return "hello";
	}
	
	// display list of Users
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listUsers", userService.getAllUsers());
		return "index";
	
	}

	@GetMapping("/userFormBoth")
	public String userFormBoth(Model model){
		User user = new User();
		model.addAttribute("user",user);
		model.addAttribute("listUsers",userService.getAllUsers());
		return "user_reg";
	}
	
	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);
		return "new_user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user, Model model) {
		// save User to database
		boolean flag = false;
		for (User userAlready : userService.getAllUsers()) {
			if(userAlready.getEmail().equals(user.getEmail())){
				flag = true;
				break;
			}
		}
		if(flag){
			model.addAttribute("fail","Email already exist!");
			return "new_user";
		}
		else{
			userService.saveUser(user);
			model.addAttribute("userLogin",("Welcome "+ user.getName()));
			model.addAttribute("userId",user.getId());
			System.out.println("Success");
			return "success_login_user";
		}
		
		
	}
	
	@GetMapping("/showFormForUserUpdate/{id}")
	public String showFormForUserUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get User from the service
		User user = userService.getUserById(id);
		
		// set User as a model attribute to pre-populate the form
		model.addAttribute("userLogin",("Welcome "+ user.getName()));
			
		model.addAttribute("userId",id);
		model.addAttribute("user", user);
		return "update_user";
	}

	@GetMapping("/preUserLogin/{id}")
	public String preUserLogin(@PathVariable(value = "id") long id, Model model){
		User user = userService.getUserById(id);

		model.addAttribute("userId",user.getId());
		model.addAttribute("user", user);
		return "logedin_user";
	}

	@PostMapping("/userLogin")
	public String userLogin(@ModelAttribute("user") User user, Model model) {
		
		User userM = userService.getUserById(user.getId());
		// long a = user.getId();
		if(userM.getPassword().equals(user.getPassword())){
			// model.addAttribute("msg2","success");
			model.addAttribute("userLogin",("Welcome "+ userM.getName()));
			model.addAttribute("userId",user.getId());
			
			System.out.println("Success");
			return "success_login_user";
		}
		else{
			model.addAttribute("msg","Wrong Username Password");
			return "logedin_user";
		}
	}

	@GetMapping("/backUser/{id}")
	public String backUser(@PathVariable(value = "id") long id, Model model){
		User user = userService.getUserById(id);
		model.addAttribute("userLogin",("Welcome "+ user.getName()));
			model.addAttribute("userId",user.getId());
			System.out.println("Success");
			return "success_login_user";
	}

	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user, Model model) {
		// save User to database
		
			userService.saveUser(user);
			model.addAttribute("userLogin",("Welcome "+ user.getName()));
			model.addAttribute("userId",user.getId());
			System.out.println("Success");
			return "success_login_user";
		
	}
	
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id") long id) {
		
		// call delete User method 
		this.userService.deleteUserById(id);
		return "redirect:/userFormBoth";
	}
}
