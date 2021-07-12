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

import com.abhishek.service.AdminService;
import com.abhishek.model.Admin;
import com.abhishek.model.Course;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

    //Admin

	
	@GetMapping("/adminDummy")
	public String adminDummy(Model model){
		
		return "admin_dummy";
	}
	
	@PostMapping("/checkAdminDummy")
	public String checkAdminDummy(@ModelAttribute("email") String email, @ModelAttribute("password") String password, Model model) {
	
		System.out.println(email);
		System.out.println(password);
		if (email.equals("dummy@admin.com")&&password.equals("dummy123")) {
			System.out.println("Success");
			Admin admin = new Admin();
			model.addAttribute("admin",admin);
			model.addAttribute("listAdmins",adminService.getAllAdmins());
			return "admin_reg";

			
		} else {
			model.addAttribute("fail","Wrong Dummy credential!");
			return "admin_dummy";
		}
	}


	@GetMapping("/adminFormBoth")
	public String adminFormBoth(Model model){
		Admin admin = new Admin();
		model.addAttribute("admin",admin);
		model.addAttribute("listAdmins",adminService.getAllAdmins());
		return "admin_reg";
	}



	@GetMapping("/showNewAdminForm")
	public String showNewAdminForm(Model model) {
		// create model attribute to bind form data
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "new_admin";
	}

	@PostMapping("/saveAdmin")
	public String saveAdmin(@ModelAttribute("admin") Admin admin,Model model) {
	
		boolean flag = false;
		for (Admin adminAlready : adminService.getAllAdmins()) {
			if(adminAlready.getEmail().equals(admin.getEmail())){
				flag = true;
				break;
			}
		}
		if(flag){
			model.addAttribute("fail","Email already exist!");
			return "new_admin";
		}
		else{
			adminService.saveAdmin(admin);
			model.addAttribute("adminLogin",("Welcome "+ admin.getName()));
			model.addAttribute("adminId",admin.getId());
			System.out.println("Success");
			return "success_login_admin";
		}
		
	}
	@GetMapping("/preAdminLogin/{id}")
	public String preAdminLogin(@PathVariable(value = "id") long id, Model model){
		Admin admin = adminService.getAdminById(id);

		model.addAttribute("adminId",id);
		model.addAttribute("admin", admin);
		return "logedin_admin";
	}

	@PostMapping("/adminLogin")
	public String adminLogin(@ModelAttribute("admin") Admin admin, Model model) {
		
		Admin adminM = adminService.getAdminById(admin.getId());
		// long a = admin.getId();
		if(adminM.getPassword().equals(admin.getPassword())){
			// model.addAttribute("msg","success");
			model.addAttribute("adminLogin",("Welcome "+ adminM.getName()));
			model.addAttribute("adminId",admin.getId());
			System.out.println("Success");

			return "success_login_admin";
		}
		else{
			model.addAttribute("adminLogin",("Welcome "+ adminM.getName()));
			model.addAttribute("adminId",admin.getId());
			model.addAttribute("msg","Wrong Username Password");
			return "logedin_admin";
		}
	}


	@GetMapping("/backAdmin/{id}")
	public String backAdmin(@PathVariable(value = "id") long id, Model model){
		Admin admin = adminService.getAdminById(id);
		model.addAttribute("adminLogin",("Welcome "+ admin.getName()));
			model.addAttribute("adminId",admin.getId());
			System.out.println("Success");
			return "success_login_admin";
	}

	@GetMapping("/showFormForAdminUpdate/{id}")
	public String showFormForUserUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get User from the service
		Admin admin = adminService.getAdminById(id);
		
		// set User as a model attribute to pre-populate the form
		model.addAttribute("adminLogin",("Welcome "+ admin.getName()));
			
		model.addAttribute("adminId",id);
		model.addAttribute("admin", admin);
		return "update_admin";
	}

	@PostMapping("/updateAdmin")
	public String updateAdmin(@ModelAttribute("admin") Admin admin,Model model) {
	
			adminService.saveAdmin(admin);
			model.addAttribute("adminLogin",("Welcome "+ admin.getName()));
			model.addAttribute("adminId",admin.getId());
			System.out.println("Success");
			return "success_login_admin";		
	}

	@GetMapping("/deleteAdmin/{id}")
	public String deleteUser(@PathVariable (value = "id") long id) {
		
		// call delete User method 
		this.adminService.deleteAdminById(id);
		return "redirect:/adminDummy";
	}


}
