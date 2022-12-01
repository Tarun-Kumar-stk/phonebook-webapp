package com.phonebook.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phonebook.crud.entity.Contact;
import com.phonebook.crud.exceptions.NoDataFoundException;
import com.phonebook.crud.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	private ContactRepository repository;
	
	public ContactServiceImpl(ContactRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean createContact(Contact theContact) {
		Contact savedContact = repository.save(theContact);
		if(savedContact != null)
			return true;
		return false;
	}

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> list = new ArrayList<>();
		list = repository.findAll();
		return list;
	}

	@Override
	public Contact getContactById(int theId) {
		Optional<Contact> optional = repository.findById(theId);
		if(optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public boolean deleteContactById(int theId) {
//		Optional<Contact> optional = repository.findById(theId);
//		if(optional.isPresent()) {
		try {
			repository.deleteById(theId);
			return true;
		}
		catch(Exception ex) {
			throw new NoDataFoundException("Unable to delete as the contact with id: " + theId + " not found in db");
		}
			
//		}
	}

}
