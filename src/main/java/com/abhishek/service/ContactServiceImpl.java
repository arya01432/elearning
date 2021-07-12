package com.abhishek.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abhishek.repository.ContactRepository;
import com.abhishek.model.Contact;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;


	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public void saveContact(Contact contact) {
		this.contactRepository.save(contact);
	}

	@Override
	public Contact getContactById(long id) {
		Optional<Contact> optional = contactRepository.findById(id);
		Contact contact = null;
		if (optional.isPresent()) {
			contact = optional.get();
		} else {
			throw new RuntimeException(" Contact not found for id :: " + id);
		}
		return contact;
	}

	@Override
	public void deleteContactById(long id) {
		this.contactRepository.deleteById(id);
	}

}
