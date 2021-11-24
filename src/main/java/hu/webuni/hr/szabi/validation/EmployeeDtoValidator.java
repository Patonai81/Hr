package hu.webuni.hr.szabi.validation;

import hu.webuni.hr.szabi.dto.EmployeeDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class EmployeeDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return EmployeeDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmployeeDto employeeDto = (EmployeeDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeName", "error.employeeName", "Name is required.");

        if (employeeDto.getSalary() < 0){
            errors.rejectValue("salary","salary");
        }

        if (employeeDto.getStartWork().isAfter(LocalDateTime.now())){
            errors.rejectValue("startWork","startWork");
        }


    }
}
