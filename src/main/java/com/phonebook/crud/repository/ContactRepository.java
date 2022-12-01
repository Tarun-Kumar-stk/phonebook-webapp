package com.phonebook.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phonebook.crud.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
