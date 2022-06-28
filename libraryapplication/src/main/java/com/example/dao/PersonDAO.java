package com.example.dao;

import com.example.models.Book;
import com.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbc;

    @Autowired
    public PersonDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Person> index() {
        return jdbc.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Optional <Person> show(String name){
        return jdbc.query("SELECT * FROM Person where full_name = ?" , new Object[]{name} , new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public Person getbyid(int id) {
        return jdbc.queryForObject("SELECT * FROM Person WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbc.update("insert into Person (full_name , person_year) values (?,?)", person.getFull_name(), person.getPerson_year());
    }

    //update Person set full_name = 'Aibolat', person_year = 1999 where person_id= 2;
    public void update(int id, Person person) {
        jdbc.update("update Person set full_name = ?, person_year = ? where person_id= ?", person.getFull_name(), person.getPerson_year(), id);
    }

    public void delete(int id) {
        jdbc.update("delete from Person where person_id = ?", id);
    }
    public List<Book> bookbyid(int id) {
        return jdbc.query("select * from Book where person_id=?" , new Object[]{id} , new BeanPropertyRowMapper<>(Book.class));
    }
}
