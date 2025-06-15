package com.first.journalApp.controller;

import com.first.journalApp.entity.AuthorEntity;
import com.first.journalApp.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/create-author")
    public ResponseEntity<?> addAuthor(@RequestBody @Valid AuthorEntity newAuthor){
        boolean isCreated = authorService.addAuthor(newAuthor);
        if(isCreated){
            return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Author Exists with email " + newAuthor.getEmail(), HttpStatus.BAD_REQUEST);
    }
}
