package com.senutech.security.app.controller;


import com.senutech.security.app.model.Person;
import com.senutech.security.app.repository.PersonRepository;
import com.senutech.security.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class PersonController {

    @Autowired private PersonService personService;
    @Autowired private PersonRepository personRepository;

    @RequestMapping("/s/persons")
    List<Person> getPersons() { return personRepository.findAll();}

    @RequestMapping("/s/person/{id}")
    Person getPersonById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return personRepository.getById(uuid);

    }
}
