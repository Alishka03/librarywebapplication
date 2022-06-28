package com.example.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person {

    private int person_id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2 , max = 40 , message = "Name should be between 2 and 30 chars")
    private String full_name;
    @Min(value = 1555 ,message =  "year is more than 0")
    @Max(value = 2016 )

    private int person_year;

    public Person(int person_id, String full_name, int person_year) {
        this.person_id = person_id;
        this.full_name = full_name;
        this.person_year = person_year;
    }

    public Person() {
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getPerson_year() {
        return person_year;
    }

    public void setPerson_year(int person_year) {
        this.person_year = person_year;
    }
}
