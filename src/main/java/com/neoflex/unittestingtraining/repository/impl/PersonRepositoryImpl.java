package com.neoflex.unittestingtraining.repository.impl;

import com.neoflex.unittestingtraining.domain.dto.PersonDto;
import com.neoflex.unittestingtraining.domain.entity.Person;
import com.neoflex.unittestingtraining.domain.mapper.PersonMapper;
import com.neoflex.unittestingtraining.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.List;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepository.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM Person", (ResultSet resultSet, int rowNum) ->
                new Person(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email")));
    }

    @Override
    public Person findOne(Long id) {
        Person person = null;

        try {
            person = jdbcTemplate.queryForObject("SELECT * FROM Person where id = ?", new Object[]{id}, ROW_MAPPER);
        } catch (DataAccessException e) {
            String error = String.format("Couldn't find entity with id %d", id);
            LOGGER.debug(error);
            throw new RuntimeException(error);
        }

        return person;
    }

    @Override
    public PersonDto save(PersonDto person) {
        jdbcTemplate.update("INSERT INTO Person(name, email) VALUES (?, ?)", person.getName(), person.getEmail());

        return person;
    }

    @Override
    public PersonDto updateById(Long id, PersonDto personDto) {
        jdbcTemplate.update("UPDATE Person SET name = ?, email = ?",
                personDto.getName(), personDto.getEmail());

        return personDto;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }
}
