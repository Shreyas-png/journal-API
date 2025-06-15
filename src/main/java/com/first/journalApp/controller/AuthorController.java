package com.first.journalApp.controller;

import com.first.journalApp.entity.AuthorEntity;
import com.first.journalApp.service.AuthorJournalService;
import com.first.journalApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorJournalService authorJournalService;

//    @GetMapping
//    public List<AuthorEntity> getAll(){
//        return authorService.getAllUsers();
//    }


    @GetMapping
    public ResponseEntity<?> getAuthorbyEmail(Principal principal){
        String email = principal.getName();
        AuthorEntity author = authorService.getAuthorByEmail(email);
        if(author == null){
            return new ResponseEntity<>("Author Does Not Exists with Email " + email, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteJournal(Principal principal){
        String email = principal.getName();
        boolean isDeleted = authorJournalService.deleteAuthor(email);
        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Author Does Not Exists with Email " + email, HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateAuthor(Principal principal, @RequestBody AuthorEntity updatedAuthor){
        String email = principal.getName();
//        System.out.println(updatedAuthor.getEmail());
        updatedAuthor = authorService.updateAuthor(email, updatedAuthor);
        if(updatedAuthor != null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Author Does Not Exists with Email " + email, HttpStatus.NOT_FOUND);
    }
}
