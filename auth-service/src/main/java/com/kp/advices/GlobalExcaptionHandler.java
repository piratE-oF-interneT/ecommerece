package com.kp.advices;


import com.kp.exceptions.LoginFailedException;
import com.kp.exceptions.ResourceNotFoundException;
import com.kp.exceptions.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcaptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)

    public ResponseEntity<ErrorResponse> handleUserALreadyExistException(UserAlreadyExistException exception){

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage() , HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginFailedException.class)

    public ResponseEntity<ErrorResponse> handleLoginFailure(LoginFailedException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage() , HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException exception){

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
    }
}
