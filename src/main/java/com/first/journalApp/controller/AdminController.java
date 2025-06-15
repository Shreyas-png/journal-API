package com.first.journalApp.controller;

import com.first.journalApp.entity.AuthorEntity;
import com.first.journalApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> addAdmin(@RequestBody AuthorEntity admin){
        boolean isCreated = authorService.addAdmin(admin);
        if(!isCreated){
            return new ResponseEntity<>("Author exists with email " + admin.getEmail(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("all-authors")
    public List<AuthorEntity> getAllAuthors(){
        return authorService.getAllAuthors();
    }
}
