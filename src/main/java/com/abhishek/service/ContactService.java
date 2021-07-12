package com.abhishek.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abhishek.model.Contact;

public interface ContactService {
	List<Contact> getAllContacts();
	void saveContact(Contact Contact);
	Contact getContactById(long id);
	void deleteContactById(long id);
	
}
