package com.neoflex.unittestingtraining.controller;

import com.neoflex.unittestingtraining.domain.entity.Person;
import com.neoflex.unittestingtraining.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class HomeController {

    @Autowired
    public HomeController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private final PersonRepository personRepository;

    @GetMapping
    public List<Person> findAl() {
        return personRepository.findAll();
    }
}
