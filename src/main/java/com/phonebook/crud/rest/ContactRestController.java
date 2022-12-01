package com.phonebook.crud.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phonebook.crud.entity.Contact;
import com.phonebook.crud.exceptions.NoDataFoundException;
import com.phonebook.crud.service.ContactService;

@RestController
@RequestMapping("/api/contacts")
public class ContactRestController {
	
	Logger logger = LoggerFactory.getLogger(ContactRestController.class);
	
	private ContactService service;
	
	public ContactRestController(ContactService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<String> saveContact(@RequestBody Contact theContact){
		
		logger.debug("** saveContact() - Execution started **");
		try {
			// just in case user sends id along the request body.
			theContact.setId(0);
			boolean  isSaved = service.createContact(theContact);
			if(isSaved) {
				logger.info("** saveContact() - Contact saved. **");
				return new ResponseEntity<>("Contact saved successfully.", HttpStatus.CREATED);
			}
				
		}catch(Exception e) {
			logger.error("** saveContact() - Exception occured " + e.getMessage());
		}
		logger.info("** saveContact() - Contact was not saved. **");
		logger.debug("** saveContact() - Execution completed **");
		return new ResponseEntity<>("Could not save the contact.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping
	public List<Contact> findAllContacts(){
		return service.getAllContacts();
	}
	
	@GetMapping("/{theId}")
	public ResponseEntity<?> getContactById(@PathVariable int theId) {
		logger.debug("** getContactById() - Execution started **");
		Contact contact = null;
		try {
			contact = service.getContactById(theId);
			if(contact == null) {
				logger.info("getContactById() - No record found ");
				throw new NoDataFoundException("Contact with id: " + theId + " not found in db"); 
//				return new ResponseEntity<>("No contact available with given id.", HttpStatus.OK);
			}
		}catch(Exception e) {
			logger.error("getContactById() - Exception occured " + e.getMessage());
		}
		logger.info("** getContactById() - contact retrieved **");
		logger.debug("** getContactById() - Execution completed **");
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateContact(@RequestBody Contact theContact){
		logger.debug("** updateContact() - Execution started **");
		try {
			boolean isUpdate = service.createContact(theContact);
			if(isUpdate) {
				logger.info("** updateContact() - contact updated **");
				return new ResponseEntity<>("Update successfull", HttpStatus.INTERNAL_SERVER_ERROR);
			}
				
		}catch(Exception e) {
			logger.error("** updateContact() - Exception occured **" + e.getMessage());
		}
		logger.info("** updateContact() - Contact not updated **");
		logger.debug("** updateContact() - Execution completed **");
		return new ResponseEntity<>("Update failed", HttpStatus.OK);
	}
	
	@DeleteMapping("{theId}")
	public ResponseEntity<String> deleteContactById(@PathVariable int theId){
		logger.debug("** deleteContactById() - Execution started **");
		try {
			boolean isDeleted = service.deleteContactById(theId);
			if(isDeleted) {
				logger.info("** deleteContactById() - Contact deleted **");
				return new ResponseEntity<>("Delete successfull.", HttpStatus.OK);	
			}
				
		}catch(Exception e) {
			logger.error("** deleteContactById() - Exception occured **" + e.getMessage());
		}
		logger.info("** deleteContactById() - Contact was not saved **");
		logger.debug("** deleteContactById() - Execution completed **");
		return new ResponseEntity<>("Delete unsuccessfull.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}












