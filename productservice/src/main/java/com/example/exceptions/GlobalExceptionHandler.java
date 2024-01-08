package com.example.exceptions;

import com.example.Dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //throughout appln from any controller exception
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class) // called when an exception is thrown in controller
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
                HttpStatus.NOT_FOUND); //we are controlling the ResponseEntity
    }
}
