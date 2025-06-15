package com.first.journalApp.controller;

import com.first.journalApp.dto.JournalRequest;
import com.first.journalApp.entity.JournalEntity;
import com.first.journalApp.service.AuthorJournalService;
import com.first.journalApp.service.JournalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;
    @Autowired
    private AuthorJournalService authorJournalService;;

    @GetMapping
    public ResponseEntity<?> getAll(Principal principal){
        String authorEmail = principal.getName();
        List<JournalEntity> journals = journalService.getAllJournalByAuthor(authorEmail);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addJournal(@RequestBody @Valid JournalRequest receivedJournal, Principal principal){
        String authorEmail = principal.getName();
        JournalEntity newJournal = authorJournalService.addJournal(authorEmail, receivedJournal);
        if(newJournal != null){
            return new ResponseEntity<>(newJournal, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Journal Exists with id " + receivedJournal.getId(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("id/{journalId}")
    public ResponseEntity<?> getJournalbyID(@PathVariable String journalId, Principal principal){
        String authorEmail = principal.getName();
        JournalEntity journal = authorJournalService.getJournalById(journalId, authorEmail);
        if(journal == null){
            return new ResponseEntity<>("Journal Does Not Exists with Id " + journalId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @DeleteMapping("id/{journalId}")
    public ResponseEntity<?> deleteJournal(@PathVariable String journalId, Principal principal){
        String authorEmail = principal.getName();
        boolean isDeleted = authorJournalService.deleteJournal(journalId, authorEmail);
        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Journal Does Not Exists with id " + journalId, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{journalId}")
    public ResponseEntity<?> updateJournal(@PathVariable String journalId, Principal principal, @RequestBody JournalEntity updatedJournal){
        String authorEmail = principal.getName();
        boolean isUpdated = authorJournalService.updateJournal(journalId, authorEmail, updatedJournal);
        if(isUpdated){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Journal Exists with id " + journalId, HttpStatus.BAD_REQUEST);
    }
}
