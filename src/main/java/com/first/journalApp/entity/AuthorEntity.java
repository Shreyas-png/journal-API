package com.first.journalApp.entity;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "author")
@Data
public class AuthorEntity {
    @Id
    @Email(message="not a valid email")
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    List<String> roles;
}
