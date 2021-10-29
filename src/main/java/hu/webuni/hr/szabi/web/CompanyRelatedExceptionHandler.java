package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.exception.CompanyCouldNotBeManipulatedException;
import hu.webuni.hr.szabi.exception.CompanyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CompanyRelatedExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<MyErrorObject> handleCompanyNotFount(CompanyNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyErrorObject(e.getMessage(),333));

    }

    @ExceptionHandler(CompanyCouldNotBeManipulatedException.class)
    public ResponseEntity<MyErrorObject> handleCompanyCannotBeCreated(CompanyCouldNotBeManipulatedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyErrorObject(e.getMessage(),444));

    }

}
