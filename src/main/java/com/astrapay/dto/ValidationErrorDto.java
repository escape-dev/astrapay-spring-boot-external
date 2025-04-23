package com.astrapay.dto;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.List;

@AllArgsConstructor
public class ValidationErrorDto {

    private Map<String, List<String>> errors;

}
