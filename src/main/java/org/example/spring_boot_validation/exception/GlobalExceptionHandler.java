package org.example.spring_boot_validation.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Payload;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;


import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.exc.InvalidFormatException;

import javax.annotation.processing.FilerException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation Errors
    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        System.out.println(" at handleValidationExceptions, MethodArgumentNotValidException");
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, String>>> handleValidationExceptionsWithSeverity(
            MethodArgumentNotValidException ex) {

        System.out.println("at handleValidationExceptionsWithSeverity");

        List<Map<String, String>> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            Map<String, String> errorMap = new HashMap<>();
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            String severity = "UNKNOWN";

            try {
                ConstraintViolation<?> violation =
                        error.unwrap(ConstraintViolation.class);

                Set<Class<? extends Payload>> payloads =
                        violation.getConstraintDescriptor()
                                .getPayload();

                severity = payloads.stream()
                        .findFirst()
                        .map(Class::getSimpleName)
                        .orElse("UNKNOWN");

            } catch (Exception e) {
                e.printStackTrace();
            }

            errorMap.put("field", fieldName);
            errorMap.put("message", errorMessage);
            errorMap.put("severity", severity);

            errors.add(errorMap);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FilerException.class)
    public ResponseEntity<Map<String, String>> handleValidationFilter(
            MethodArgumentNotValidException ex) {
        System.out.println(" at handleValidationFilter, FilerException");
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraint(
            ConstraintViolationException ex) {

        System.out.println(" at handleConstraint, ConstraintViolationException");
        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }

    /*private String extractSeverity(FieldError error) {

        if (error.contains(ConstraintViolation.class)) {

            ConstraintViolation<?> violation =
                    error.unwrap(ConstraintViolation.class);

            ConstraintDescriptor<?> descriptor =
                    violation.getConstraintDescriptor();

            Set<Class<? extends Payload>> payloads =
                    descriptor.getPayload();

            if (!payloads.isEmpty()) {
                return payloads.iterator()
                        .next()
                        .getSimpleName();
            }
        }
        return "Unknown";
    }*/

}
