package com.github.vickyrai01.salesmanagement.exception;

import com.github.vickyrai01.salesmanagement.exception.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {

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

}