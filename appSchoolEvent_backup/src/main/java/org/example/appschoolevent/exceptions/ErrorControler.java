package org.example.appschoolevent.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorControler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> controlarErrores(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();
        for  (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ElementosNoEncontrados.class)
    public ResponseEntity<Map<String, String>> manejarElementosNoEncontrados(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EliminarNoExistente.class)
    public ResponseEntity<Map<String, String>> manejarEliminarNoExistente(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarIllegalArgument(
            IllegalArgumentException exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
