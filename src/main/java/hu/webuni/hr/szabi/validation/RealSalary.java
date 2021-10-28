package hu.webuni.hr.szabi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SalaryNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RealSalary {
    String message() default "hu.webuni.hr.szabi.validation.RealSalary.message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}