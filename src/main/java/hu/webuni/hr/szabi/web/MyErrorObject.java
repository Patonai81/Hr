package hu.webuni.hr.szabi.web;

import org.springframework.validation.FieldError;

import java.util.List;

public class MyErrorObject {

    private String errorText;
    private Integer errorCode;
    private List<FieldError> fieldErrorList;

    public MyErrorObject() {
    }

    public MyErrorObject(String errorText, Integer errorCode, List<FieldError> fieldErrorList) {
        this(errorText,errorCode);
        this.fieldErrorList = fieldErrorList;

    }

    public MyErrorObject(String errorText, Integer errorCode) {
        this.errorText = errorText;
        this.errorCode = errorCode;
    }


    public List<FieldError> getFieldErrorList() {
        return fieldErrorList;
    }

    public void setFieldErrorList(List<FieldError> fieldErrorList) {
        this.fieldErrorList = fieldErrorList;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
