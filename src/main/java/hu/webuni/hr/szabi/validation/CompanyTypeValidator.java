package hu.webuni.hr.szabi.validation;

import hu.webuni.hr.szabi.model.CompanyTypeFromDB;
import hu.webuni.hr.szabi.repository.CompanyTypeRepository;
import hu.webuni.hr.szabi.service.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

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
        System.out.println("CompanyConstaintValidator is running...");
        System.out.println("Incoming parameter is :"+companyTypeFromDB);
        if (!availableCompanyTypes.contains(companyTypeFromDB)){
           return false;
        }
    return true;
    }

}
