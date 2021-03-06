package com.example.controllers;

import com.example.dao.BookDAO;
import com.example.dao.PersonDAO;
import com.example.models.Book;
import com.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books" ,bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getbyid(id));

        Optional<Person> bookOwner = bookDAO.getowner(id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @GetMapping("/new")
    public String newbook(Model model){
        model.addAttribute("book" , new Book());
        return "books/new";
    }
    @PostMapping()
    public String submitnewbook(@ModelAttribute("book")@Valid Book book , BindingResult bind){
        if(bind.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }
    @GetMapping("{id}/edit")
    public String editingbook(@ModelAttribute("id") int id , Model model){
        model.addAttribute("book",bookDAO.getbyid(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String acceptupdate (@ModelAttribute("book") @Valid  Book book ,BindingResult bind ,  @PathVariable("id") int id){
        if(bind.hasErrors())
            return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/"+id;
    }
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,@ModelAttribute("person") Person person){
        bookDAO.assign(id,person);
        return  "redirect:/books/"+id;

    }
}

