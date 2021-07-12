package com.abhishek.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abhishek.repository.AdminRepository;
import com.abhishek.model.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository AdminRepository;


	@Override
	public List<Admin> getAllAdmins() {
		return AdminRepository.findAll();
	}

	@Override
	public void saveAdmin(Admin admin) {
		this.AdminRepository.save(admin);
	}

	@Override
	public Admin getAdminById(long id) {
		Optional<Admin> optional = AdminRepository.findById(id);
		Admin admin = null;
		if (optional.isPresent()) {
			admin = optional.get();
		} else {
			throw new RuntimeException(" Admin not found for id :: " + id);
		}
		return admin;
	}

	@Override
	public void deleteAdminById(long id) {
		this.AdminRepository.deleteById(id);
	}

}
