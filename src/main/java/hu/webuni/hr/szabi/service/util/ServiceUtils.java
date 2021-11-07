package hu.webuni.hr.szabi.service.util;
import hu.webuni.hr.szabi.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceUtils {
    private static ServiceUtils instance;

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    @PostConstruct
    public void fillInstance() {
        instance = this;
    }

    public static CompanyTypeRepository getCompanyTypeRepository() {
        return instance.companyTypeRepository;
    }
}