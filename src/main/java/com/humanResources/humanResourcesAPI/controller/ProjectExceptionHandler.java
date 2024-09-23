package com.humanResources.humanResourcesAPI.controller;

import com.humanResources.humanResourcesAPI.model.Exception.ProjectNotFoundException;
import com.humanResources.humanResourcesAPI.vo.StandarResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ProjectExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<?>handleProjectNotFoundException(ProjectNotFoundException ex){
        StandarResponse response = StandarResponse
            .builder()
            .message(ex.getMessage())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>handleValidationException(MethodArgumentNotValidException ex){
        HashMap<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        StandarResponse response = StandarResponse
                .builder()
                .message("Error en el cuerpo del request")
                .data(errors)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
