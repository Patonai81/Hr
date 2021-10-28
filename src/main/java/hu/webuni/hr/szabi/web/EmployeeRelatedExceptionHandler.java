package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.exception.EmployeeCouldNotBeCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeRelatedExceptionHandler {

    @ExceptionHandler(EmployeeCouldNotBeCreatedException.class)
    public ResponseEntity<MyErrorObject> handleEmployeeCouldNotBeCreatedException(EmployeeCouldNotBeCreatedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyErrorObject(e.getMessage(),222));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorObject> handleValidation(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyErrorObject(e.getMessage(),222,e.getBindingResult().getFieldErrors()));

    }


}
