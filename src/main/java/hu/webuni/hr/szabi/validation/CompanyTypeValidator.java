package hu.webuni.hr.szabi.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.webuni.hr.szabi.exception.CompanyCouldNotBeManipulatedException;
import hu.webuni.hr.szabi.model.CompanyTypeFromDB;
import hu.webuni.hr.szabi.repository.CompanyTypeRepository;
import hu.webuni.hr.szabi.service.util.ServiceUtils;

public class CompanyTypeValidator  implements ConstraintValidator<CompanyTypeDB, CompanyTypeFromDB> {

    @Autowired
    CompanyTypeRepository companyTypeRepository;
    
    private List<CompanyTypeFromDB> availableCompanyTypes;

    @Override
    public void initialize(CompanyTypeDB constraintAnnotation) {
        companyTypeRepository = ServiceUtils.getCompanyTypeRepository();
        availableCompanyTypes = companyTypeRepository.findAll();
    }

    @Override
    public boolean isValid(CompanyTypeFromDB companyTypeFromDB, ConstraintValidatorContext constraintValidatorContext) {
        if (!availableCompanyTypes.contains(companyTypeFromDB)){
            throw new CompanyCouldNotBeManipulatedException("The given company type is not permitted!!"+companyTypeFromDB);
        }
    return true;
    }

}
