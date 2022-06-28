package com.example.controllers;

import com.example.dao.PersonDAO;
import com.example.models.Person;
import com.example.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people" , personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int  id,Model model){
        model.addAttribute("person" , personDAO.getbyid(id));
        boolean flag = (personDAO.bookbyid(id).size()==0);
        System.out.println(flag);
        model.addAttribute("condition" , flag);
        model.addAttribute("books" , personDAO.bookbyid(id));
        return "people/show";
    }
    //insert into Person (full_name , person_year) values (?,?)
    @GetMapping("/new")
    public String newperson(Model model){
        model.addAttribute("person" , new Person());
        return "people/new";
    }
    @PostMapping()
    public String accept(@ModelAttribute("person")@Valid Person person , BindingResult bind){
        personValidator.validate(person , bind);
        if(bind.hasErrors())
            return "people/new";
        personDAO.save(person);
        return "redirect:/people";
    }
    //
    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id,Model model){
        model.addAttribute("person" , personDAO.getbyid(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String acceptupdate (@ModelAttribute("person") @Valid Person person ,BindingResult bind ,@PathVariable("id") int id){
        if(bind.hasErrors())
            return "people/edit";
        personDAO.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public  String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
