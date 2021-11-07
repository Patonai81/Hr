package hu.webuni.hr.szabi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CompanyTypeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyType {
    String message() default "{hu.webuni.hr.szabi.validation.CompanyType.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
