package com.iv1201.recapp.Config.Exceptions.Util;

import com.iv1201.recapp.Config.Exceptions.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception handling class for the application. Advice for the client on
 * the types of exceptions thrown on server.
 */

@RestControllerAdvice
public class AppExceptionHandler {

    /**
     * Handle invalid arguments of class <code>MethodArgumentNotValidException</code>.
     * @param e input of exceptions that might occur due to validation.
     * @return exceptions that occurred due to validation not being correct, sent client.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e){
        Map<String, String> expMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            expMap.put(error.getField(), error.getDefaultMessage());
        });
        return expMap;
    }

    /**
     * Handles exception due to custom validation identifier not valid data.
     * @param e the constraint exception that occurred due to validation.
     * @return exceptions that occurred due to custom validation not being correct, sent to client.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleInvalidArgument(ConstraintViolationException e){
        Map<String, String> expMap = new HashMap<>();
        e.getConstraintViolations().forEach(error -> {
            expMap.put(error.getPropertyPath().toString(), error.getMessageTemplate());
        });
        return expMap;
    }

    /**
     * Handle Run time exceptions
     * @param e the Run time exception that occurred.
     * @return <code>ExceptionDTO</code> with error message for client to recieve.
     */

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionsDTO> handleInvalidArgument(RuntimeException e){
        return ResponseEntity.status(500).body(new ExceptionsDTO(e.getMessage()));
    }

    /**
     * Handle when trying to access object which has missing fields.
     * @param e incoming exception that occurred.
     * @return message to client that fields are missing.
     */
    @ExceptionHandler(NoSuchFieldError.class)
    public Map<String, String> handleInvalidArgument(NoSuchFieldError e){
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(403).body(expMap).getBody();
    }

    /**
     * Handle expiration of tokens.
     * @param e token has expired caught by Jwt filter
     * @return message for the client that token has expired, so it can be handled
     * on client side.
     */
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> handleBusinessException(TokenExpiredException e){
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(403).body(expMap.toString());
    }

    /**
     * Handle emails that does not exist in database.
     * @param e exception that email does not exist in database.
     * @return message for client that email does not exist.
     */
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<String> handleBusinessException(EmailNotFoundException e){
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(403).body(expMap.toString());
    }

    /**
     * Handle already existing emails when signing up user.
     * @param e exception that email already exist in database.
     * @return message for client that email already exist.
     */
    @ExceptionHandler(EmailAllreadyExcistsException.class)
    public ResponseEntity<String> handleBusinessException(EmailAllreadyExcistsException e){
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(406).body(expMap.toString());
    }

    /**
     * Handle faulty competence that does not exist in database.
     * @param e exception that competence does not exist in database.
     * @return message for client that competence does not exist.
     */
    @ExceptionHandler(CouldNotFindCompetencesException.class)
    public ResponseEntity<String> handleBusinessException(CouldNotFindCompetencesException e){
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(500).body(expMap.toString());
    }

    /**
     * Handle exception that application could not be submitted.
     * @param e exception that application could not be submitted.
     * @return message for client that application could not be submitted.
     */
    @ExceptionHandler(ApplicationCouldNotSubmitException.class)
    public ResponseEntity<String> handleBusinessException(ApplicationCouldNotSubmitException e) {
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(500).body(expMap.toString());
    }

    /**
     * Handle exceptions due to malfunction in recruiter service.
     * @param e exception that occurred in <code>RecruiterService</code>.
     * @return <code>ExceptionDTO</code> with exception message for client.
     */
    @ExceptionHandler(RecruiterException.class)
    public ResponseEntity<ExceptionsDTO> handleBusinessException(RecruiterException e){
        int statusCode = 400;
        if(e.getMessage().contains("updated")){
            statusCode = 500;
        }else if(e.getMessage().contains("Could not find get All Applicants")){
            statusCode = 500;
        }
        return ResponseEntity.status(statusCode).body(new ExceptionsDTO(e.getMessage()));
    }
}
