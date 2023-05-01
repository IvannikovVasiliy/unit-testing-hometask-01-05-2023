package com.neoflex.unittestingtraining.repository;

import com.neoflex.unittestingtraining.domain.entity.Person;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import java.sql.*;

public interface PersonRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Person
    RowMapper<Person> ROW_MAPPER =
            (ResultSet resultSet, int rowNum) ->
                    new Person(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("email"));
    List<Person> findAll();
    Person findOne(String id);
    Person save(Person person);
    int delete(String id);
}
