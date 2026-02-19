package com.cts.exception;


import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
     var errors = ex.getBindingResult().getFieldErrors().stream()
             .collect(java.util.stream.Collectors.toMap(
                     fe -> fe.getField(),
                     fe -> fe.getDefaultMessage(),
                     (a, b) -> a
             ));
     return ResponseEntity.badRequest().body(Map.of(
             "message", "Validation failed",
             "errors", errors
     ));
 }

 @ExceptionHandler(IllegalArgumentException.class)
 public ResponseEntity<?> illegal(IllegalArgumentException ex) {
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
 }
}
