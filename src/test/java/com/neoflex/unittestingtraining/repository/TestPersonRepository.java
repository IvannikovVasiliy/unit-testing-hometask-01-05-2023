package com.neoflex.unittestingtraining.repository;

import com.neoflex.unittestingtraining.domain.entity.Person;
import com.neoflex.unittestingtraining.repository.impl.PersonRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class TestPersonRepository {

    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();

        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        personRepository = new PersonRepositoryImpl(jdbcTemplate);
    }

//    @Test
//    public void testFindAll() {
//        Assertions.assertNotNull(personRepository.findAll());
//        int size = personRepository.findAll().size();
//
//        Assertions.assertEquals(3, size);
//    }

    @Test
    public void testFindOne() {
        Person person = personRepository.findOne(1L);

        Assertions.assertNotNull(person);
        Assertions.assertEquals(person.getName(), "Jack Daniels");
        Assertions.assertEquals(person.getEmail(), "jackdaniels@example.com");

        Assertions.assertThrows(RuntimeException.class, () -> personRepository.findOne(10000000L));
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }
}
