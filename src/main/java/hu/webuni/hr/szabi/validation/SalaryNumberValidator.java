package hu.webuni.hr.szabi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalaryNumberValidator implements ConstraintValidator<RealSalary,Integer> {

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer>0;
    }
}
