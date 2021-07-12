package com.abhishek.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abhishek.model.User;

public interface UserService {
	List<User> getAllUsers();
	void saveUser(User user);
	User getUserById(long id);
	void deleteUserById(long id);
	
}
