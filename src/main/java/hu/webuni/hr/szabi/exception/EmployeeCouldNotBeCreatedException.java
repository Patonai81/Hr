package hu.webuni.hr.szabi.exception;

public class EmployeeCouldNotBeCreatedException extends RuntimeException{
    public EmployeeCouldNotBeCreatedException(String message) {
        super(message);
    }
}
