package com.example.dao;

import com.example.models.Book;
import com.example.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//select * from Person where person_id = (select person_id from book where book_id = 2);
@Component
public class BookDAO {
    private final JdbcTemplate jdbc;

    public BookDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public List<Book> index(){
        return jdbc.query("SELECT * FROM Book" , new BeanPropertyRowMapper<>(Book.class));
    }
    public Book getbyid(int id){
        return jdbc.query("select * from Book where book_id=?;" , new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public void save(Book book){
        jdbc.update("insert into Book (book_name,author,book_year) VALUES (?,?,?);" , book.getBook_name() , book.getAuthor() , book.getBook_year());
    }
    public void update(int id, Book book){
        jdbc.update("update Book set book_name = ?, author = ?, book_year = ? where book_id = ?" , book.getBook_name() , book.getAuthor() , book.getBook_year() , id);
    }
    public void delete(int id){
        jdbc.update("delete from Book where book_id = ?" , id);
    }
    public Optional<Person> getowner(int id){
        return jdbc.query("select * from Person where person_id = (select person_id from book where book_id = ?)" ,
                new Object[]{id} , new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    public void release(int id){
        jdbc.update("UPDATE Book set person_id = NULL where book_id=?" , id);
    }
    public void assign(int id, Person selectedPerson){
        jdbc.update("UPDATE Book set person_id = ? where book_id = ?" , selectedPerson.getPerson_id() , id);
    }
}
