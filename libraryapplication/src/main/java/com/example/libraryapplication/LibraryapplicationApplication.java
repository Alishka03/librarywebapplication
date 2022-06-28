package com.example.libraryapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class LibraryapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryapplicationApplication.class, args);
	}

}
