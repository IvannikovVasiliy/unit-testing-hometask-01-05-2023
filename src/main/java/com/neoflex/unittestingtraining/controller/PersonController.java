package com.neoflex.unittestingtraining.controller;

import com.neoflex.unittestingtraining.domain.dto.PersonDto;
import com.neoflex.unittestingtraining.domain.entity.Person;
import com.neoflex.unittestingtraining.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private final PersonRepository personRepository; // вообще-то здесь сервис должен быть, но мне лень

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        List<Person> people = personRepository.findAll();

        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Person person = personRepository.findOne(id);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDto> save(@RequestBody PersonDto person) {
        PersonDto response = personRepository.save(person);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PersonDto> editPersonById(@PathVariable Long id, @RequestBody PersonDto personDto) {
        PersonDto response = personRepository.updateById(id, personDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonById(@PathVariable Long id) {
        personRepository.delete(id);

        String response = String.format("The user with id %d was DELETED", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
