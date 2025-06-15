package com.first.journalApp.repository;

import com.first.journalApp.entity.JournalEntity;
import com.first.journalApp.entity.AuthorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface JournalRepository extends MongoRepository<JournalEntity, String> {
    List<JournalEntity> findJournalByAuthor(AuthorEntity author);
    void deleteByAuthor(AuthorEntity authorEntity);
}