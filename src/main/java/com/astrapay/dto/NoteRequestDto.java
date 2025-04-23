package com.astrapay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NoteRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

}
