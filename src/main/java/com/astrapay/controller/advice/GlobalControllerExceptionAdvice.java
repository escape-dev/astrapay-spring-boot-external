package com.astrapay.controller.advice;

import com.astrapay.dto.BaseResponsesDto;

import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import org.springframework.core.annotation.Order;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponsesDto<Map<String, List<String>>>> handleNotValidArgumentException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())
                ));

        BaseResponsesDto<Map<String, List<String>>> response  = new BaseResponsesDto<>(400, "Bad Request", errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponsesDto<Void>> handleResponseStatusException(ResponseStatusException exception) {
        BaseResponsesDto<Void> errors = new BaseResponsesDto<>(exception.getStatus().value(), exception.getMessage(), null);

        return new ResponseEntity<>(errors, exception.getStatus());
    }

}
