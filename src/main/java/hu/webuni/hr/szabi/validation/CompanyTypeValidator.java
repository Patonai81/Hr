package hu.webuni.hr.szabi.validation;

import hu.webuni.hr.szabi.model.CompanyTypeFromDB;
import hu.webuni.hr.szabi.repository.CompanyTypeRepository;
import hu.webuni.hr.szabi.service.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyTypeValidator  implements ConstraintValidator<CompanyType, CompanyTypeFromDB> {

    @Autowired
    CompanyTypeRepository companyTypeRepository;

    @Override
    public void initialize(CompanyType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        companyTypeRepository = ServiceUtils.getCompanyTypeRepository();
    }

    @Override
    public boolean isValid(CompanyTypeFromDB companyTypeFromDB, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Take a look!!!");
        System.out.println(companyTypeRepository.findAll());
        return true;
    }
}
