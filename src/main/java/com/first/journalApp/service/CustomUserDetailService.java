package com.first.journalApp.service;

import com.first.journalApp.entity.AuthorEntity;
import com.first.journalApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        AuthorEntity author = authorRepository.findAuthorByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Author Not Found"));

        return User
                .withUsername(author.getEmail())
                .password(author.getPassword())
                .roles(author.getRoles().toArray(new String[0]))
                .build();
    }
}
