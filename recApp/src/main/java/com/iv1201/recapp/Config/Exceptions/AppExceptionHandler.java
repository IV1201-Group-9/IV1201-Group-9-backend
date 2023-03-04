package com.iv1201.recapp.Config.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e){
        Map<String, String> expMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            expMap.put(error.getField(), error.getDefaultMessage());
        });
        return expMap;
    }

    @ExceptionHandler(NoSuchFieldError.class)
    public Map<String, String> handleInvalidArgument(NoSuchFieldError e){
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(403).body(expMap).getBody();
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<String> handleBusinessException(EmailNotFoundException e){
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(403).body(expMap.toString());
    }

    @ExceptionHandler(EmailAllreadyExcistsException.class)
    public ResponseEntity<String> handleBusinessException(EmailAllreadyExcistsException e){
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(406).body(expMap.toString());
    }

    @ExceptionHandler(CouldNotFindCompetencesException.class)
    public ResponseEntity<String> handleBusinessException(CouldNotFindCompetencesException e){
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(500).body(expMap.toString());
    }

    @ExceptionHandler(ApplicationCouldNotSubmitException.class)
    public ResponseEntity<String> handleBusinessException(ApplicationCouldNotSubmitException e) {
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());

        return ResponseEntity.status(500).body(expMap.toString());
    }

    @ExceptionHandler(StatusDTOException.class)
    public ResponseEntity<String> handleBusinessException(StatusDTOException e){
        int statusCode = 400;
        Map<String, String> expMap = new HashMap<>();
        System.out.println(e.getMessage());
        expMap.put("ErrorMessage", e.getMessage());
        if(e.getMessage().contains("updated")){
            statusCode = 500;
        }else if(e.getMessage().contains("Could not find getAllApplicants")){
            statusCode = 500;
        }
        return ResponseEntity.status(statusCode).body(expMap.toString());
    }
}
