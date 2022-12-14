package com.phonebook.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.phonebook.crud.entity.Contact;
import com.phonebook.crud.repository.ContactRepository;

@WebMvcTest(value=ContactServiceImpl.class)
public class TestContactServiceImpl {
	
	@MockBean
	private ContactRepository contactRepository;
	
	@Autowired
	private ContactService contactService;
	
	@Test
	public void testCreateContact_01() {
		Contact contact = new Contact(101, "Sunil", "89898989", "sunil@yahoo.com");
		Mockito.when(contactRepository.save(Mockito.any())).thenReturn(contact);
		assertEquals(true, contactService.createContact(contact));
	}
	
	@Test
	public void testCreateContact_02() {
		Contact contact = null;
		Mockito.when(contactRepository.save(Mockito.any())).thenReturn(contact);
		assertEquals(false, contactService.createContact(contact));
	}
	
	@Test
	public void testGetAllContacts() {
		Mockito.when(contactRepository.findAll()).thenReturn(Collections.emptyList());
		
		List<Contact> list = new ArrayList<>();
		assertEquals(list, contactService.getAllContacts());
	}

}








