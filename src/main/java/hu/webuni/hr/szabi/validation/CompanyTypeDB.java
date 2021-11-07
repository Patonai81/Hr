package hu.webuni.hr.szabi.validation;

import org.springframework.lang.NonNull;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CompanyTypeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyTypeDB  {
    String message() default "{hu.webuni.hr.szabi.validation.CompanyType.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
