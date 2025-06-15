package com.first.journalApp.service;

import com.first.journalApp.dto.JournalRequest;
import com.first.journalApp.entity.JournalEntity;
import com.first.journalApp.entity.AuthorEntity;
import com.first.journalApp.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private AuthorService authorService;


    public List<JournalEntity> getAllJournalByAuthor(String authorEmail){
        AuthorEntity author =  authorService.getAuthorByEmail(authorEmail);
//        if(author == null) return null
        return journalRepository.findJournalByAuthor(author);
    }

}
