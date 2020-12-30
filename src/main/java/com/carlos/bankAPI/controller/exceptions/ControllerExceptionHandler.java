package com.carlos.bankAPI.controller.exceptions;

import com.carlos.bankAPI.services.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(CpfAlreadyExistException.class)
    public ResponseEntity<StandardError> cpfAlreadyExst(CpfAlreadyExistException e, HttpServletRequest request){
        String error = "CPF Already Exist";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

   @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<StandardError> constraintViolationException(ConstraintException e, HttpServletRequest request){
       String error = "Invalid CPF";
       HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
       StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(), request.getRequestURI());

       return ResponseEntity.status(status).body(err);

   }

}
