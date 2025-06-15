package com.first.journalApp.repository;

import com.first.journalApp.entity.AuthorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<AuthorEntity, String> {
    Optional<AuthorEntity> findAuthorByEmail(String email);
    void deleteAuthorByEmail(String Email);
}
