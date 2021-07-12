package com.abhishek.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abhishek.model.Admin;

public interface AdminService {
	void saveAdmin(Admin admin);
    List<Admin> getAllAdmins();
	Admin getAdminById(long id);
	void deleteAdminById(long id);
	
}
