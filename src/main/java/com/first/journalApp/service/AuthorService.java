package com.first.journalApp.service;

import com.first.journalApp.entity.AuthorEntity;
import com.first.journalApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addAuthor(AuthorEntity newAuthor){
        Optional<AuthorEntity> Author = authorRepository.findAuthorByEmail(newAuthor.getEmail());
        if(Author.isPresent()){
            return false;
        }
        String password = passwordEncoder.encode(newAuthor.getPassword());
        newAuthor.setPassword(password);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        newAuthor.setRoles(roles);
        authorRepository.save(newAuthor);
        return true;
    }

    public boolean addAdmin(AuthorEntity admin){
        Optional<AuthorEntity> Author = authorRepository.findAuthorByEmail(admin.getEmail());
        if(Author.isPresent()){
            return false;
        }
        String password = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(password);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        admin.setRoles(roles);
        authorRepository.save(admin);
        return true;
    }

    public List<AuthorEntity> getAllAuthors(){
        return authorRepository.findAll();
    }

    public AuthorEntity getAuthorByEmail(String email){
        Optional<AuthorEntity> Author = authorRepository.findAuthorByEmail(email);
        return Author.orElse(null);
    }

    public AuthorEntity updateAuthor(String email, AuthorEntity updatedAuthor){
        Optional<AuthorEntity> Author = authorRepository.findAuthorByEmail(email);
        if(Author.isEmpty()){
            return null;
        }
        AuthorEntity oldAuthor = Author.get();
//        if(updatedAuthor.getEmail() != null && !updatedAuthor.getEmail().isEmpty()){
//            System.out.println("Working");
//            oldAuthor.setEmail(updatedAuthor.getEmail());
//        }
        if(updatedAuthor.getPassword() != null && !updatedAuthor.getPassword().isEmpty()){
            String password = passwordEncoder.encode(updatedAuthor.getPassword());
            oldAuthor.setPassword(password);
        }

        authorRepository.deleteAuthorByEmail(email);
        authorRepository.save(oldAuthor);
        return oldAuthor;
    }
}
