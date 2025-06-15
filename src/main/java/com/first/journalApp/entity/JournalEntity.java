package com.first.journalApp.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journal")
@Data
public class JournalEntity {
    @NotBlank
    @NotNull
    @Size(min=2, max=150)
    private String name;

    @NotBlank
    @NotNull
    private String content;

    @DBRef
    private AuthorEntity author;

    @Id
    private String id;

}
