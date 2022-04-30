package com.senutech.security.app.service;

import com.senutech.security.app.model.Person;
import com.senutech.security.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.senutech.security.app.repository.PersonRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class PersonService {


    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired private PersonRepository personRepository;


    public List<Person> getPersonList() {
        return personRepository.findAll();
    }


}
