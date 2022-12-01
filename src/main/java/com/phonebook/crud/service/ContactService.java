package com.phonebook.crud.service;

import java.util.List;

import com.phonebook.crud.entity.Contact;

public interface ContactService {

	public boolean createContact(Contact theContact);
	
	public List<Contact> getAllContacts();
	
	public Contact getContactById(int theId);
	
	public boolean deleteContactById(int theId);
	
}
