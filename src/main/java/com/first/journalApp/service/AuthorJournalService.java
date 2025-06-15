package com.first.journalApp.service;

import com.first.journalApp.dto.JournalRequest;
import com.first.journalApp.entity.AuthorEntity;
import com.first.journalApp.entity.JournalEntity;
import com.first.journalApp.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.first.journalApp.repository.AuthorRepository;

import java.util.Optional;

@Component
public class AuthorJournalService {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private JournalService journalService;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private JournalRepository journalRepository;

    public JournalEntity addJournal(String authorEmail, JournalRequest receivedJournal){
        AuthorEntity author = authorService.getAuthorByEmail(authorEmail);
        if(author == null) return null;

        JournalEntity newJournal = new JournalEntity();
        newJournal.setName(receivedJournal.getName());
        newJournal.setContent(receivedJournal.getContent());
        newJournal.setAuthor(author);
        newJournal.setId(receivedJournal.getId());
        if(journalRepository.findById(newJournal.getId()).orElse(null) != null){
            return null;
        }
        journalRepository.save(newJournal);
        return newJournal;
    }

    @Transactional
    public boolean deleteAuthor(String email){
        Optional<AuthorEntity> Author = authorRepository.findAuthorByEmail(email);
        if(Author.isEmpty()){ 
            return false;
        }
        deleteJournalByAuthor(Author.get());
        authorRepository.deleteAuthorByEmail(email);
        return true;
    }

    public JournalEntity getJournalById(String id, String authorEmail){
        Optional<JournalEntity> journal = journalRepository.findById(id);
        Optional<AuthorEntity> author = authorRepository.findAuthorByEmail(authorEmail);
        if(author.isEmpty() || journal.isEmpty() || !journal.get().getAuthor().equals(author.get())){
            return null;
        }
        return journal.get();
    }

    public boolean deleteJournal(String id, String authorEmail){
        Optional<JournalEntity> journal = journalRepository.findById(id);
        Optional<AuthorEntity> author = authorRepository.findAuthorByEmail(authorEmail);
        if(author.isEmpty() || journal.isEmpty() || !journal.get().getAuthor().equals(author.get())){
            return false;
        }

        journalRepository.deleteById(id);
        return true;
    }

    public void deleteJournalByAuthor(AuthorEntity authorEntity){
        journalRepository.deleteByAuthor(authorEntity);
    }

    public boolean updateJournal(String id, String authorEmail, JournalEntity updatedJournal){
        Optional<JournalEntity> journal = journalRepository.findById(id);
        Optional<AuthorEntity> author = authorRepository.findAuthorByEmail(authorEmail);
        if(author.isEmpty() || journal.isEmpty() || !journal.get().getAuthor().equals(author.get())){
            return false;
        }
        JournalEntity oldJournal = journal.get();
        oldJournal.setContent(updatedJournal.getContent() == null || updatedJournal.getContent().isEmpty() ? oldJournal.getContent() : updatedJournal.getContent());
        oldJournal.setName(updatedJournal.getName() == null || updatedJournal.getName().isEmpty() ? oldJournal.getName() : updatedJournal.getName());
        journalRepository.save(oldJournal);
        return true;
    }
}
