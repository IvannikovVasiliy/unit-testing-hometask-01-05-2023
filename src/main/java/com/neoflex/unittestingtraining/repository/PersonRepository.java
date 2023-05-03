package com.neoflex.unittestingtraining.repository;

import com.neoflex.unittestingtraining.domain.dto.PersonDto;
import com.neoflex.unittestingtraining.domain.entity.Person;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import java.sql.*;

public interface PersonRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Person
    RowMapper<Person> ROW_MAPPER =
            (ResultSet resultSet, int rowNum) ->
                    new Person(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"));
    List<Person> findAll();
    Person findOne(Long id);
    PersonDto save(PersonDto person);
    PersonDto updateById(Long id, PersonDto personDto);
    void delete(Long id);
}
