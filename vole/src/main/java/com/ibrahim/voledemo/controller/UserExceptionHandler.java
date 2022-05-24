package com.ibrahim.voledemo.controller;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> userExc(RuntimeException e){
        UserErrorResponse response=new UserErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> genericExc(Exception e){
        UserErrorResponse response=new UserErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
