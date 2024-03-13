package com.proyect_Ignacio.Proyect.Exception;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobaExeptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerArgumentException(IllegalArgumentException ex)
    {
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handlerRuntimeException(RuntimeException ex)
    {
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex,WebRequest request) 
    {
        Map<String ,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message","User not found");
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserAlreadyExist(UserAlreadyExistException ex,WebRequest request) 
    {
        Map<String ,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message","User already exist");
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }

}
