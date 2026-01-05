package com.github.vickyrai01.salesmanagement.exception;

import com.github.vickyrai01.salesmanagement.exception.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        ErrorMessage body = new ErrorMessage();
        body.setStatus(404);
        body.setError("NOT_FOUND");
        body.setMessage(ex.getMessage());
        body.setPath(req.getRequestURI());
        body.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyExists(AlreadyExistsException ex, HttpServletRequest req) {
        ErrorMessage body = new ErrorMessage();
        body.setStatus(409);
        body.setError("ALREADY_EXISTS");
        body.setMessage(ex.getMessage());
        body.setPath(req.getRequestURI());
        body.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneric(Exception ex, HttpServletRequest req) {

        ErrorMessage body = new ErrorMessage();
        body.setStatus(500);
        body.setError("INTERNAL_SERVER_ERROR");
        body.setMessage("Unexpected error");
        body.setPath(req.getRequestURI());
        body.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}