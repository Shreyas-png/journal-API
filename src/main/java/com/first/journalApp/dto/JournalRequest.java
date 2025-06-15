package com.first.journalApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JournalRequest {
    @NotBlank
    @Size(min=2, max=150)
    private String name;

    @NotBlank
    private String content;

    @NotBlank
    private String id;

}
