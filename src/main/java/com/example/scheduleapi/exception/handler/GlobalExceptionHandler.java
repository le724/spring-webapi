package com.example.scheduleapi.exception.handler;

import com.example.scheduleapi.dto.Schedule;
import com.example.scheduleapi.exception.ScheduleArgumentNotValidException;
import com.example.scheduleapi.exception.ScheduleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<String> handlerScheduleNotFoundException(ScheduleNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        // return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ScheduleArgumentNotValidException.class)
    public ResponseEntity<String> handlerScheduleArgumentNotValidException(ScheduleNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
