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

import com.abhishek.model.Admin;
import com.abhishek.model.Contact;
import com.abhishek.model.User;
import com.abhishek.service.UserService;
import com.abhishek.service.AdminService;
import com.abhishek.service.ContactService;



@Controller
public class ContactController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private ContactService contactService;

	@GetMapping("/contact/{id}")
	public String contact(@PathVariable(value = "id") long id, Model model){
		User user = userService.getUserById(id);
		model.addAttribute("user",user);
		Contact contact = new Contact();
		contact.setUserId(user.getId());
		contact.setPhoneNo(user.getPhoneNo());

		model.addAttribute("userId",id);
		model.addAttribute("userLogin",("Welcome "+ user.getName()));
		model.addAttribute("contact",contact);
		return "contact_form";
	}

	@PostMapping("/saveContact")
	public String saveContact(@ModelAttribute("contact") Contact contact,Model model) {
		// save User to database
		contactService.saveContact(contact);
		model.addAttribute("userLogin",("Welcome "+ contact.getName()));
		model.addAttribute("userId",contact.getUserId());

		return "success_login_user";
	}

	@GetMapping("/contactView/{id}")
	public String contactView(@PathVariable(value = "id") long id, Model model){
		model.addAttribute("listContacts",contactService.getAllContacts());
		model.addAttribute("adminLogin",("Welcome "+adminService.getAdminById(id).getName()));
		model.addAttribute("adminId",id);
		return "contact_view";
	}
}
