package hu.webuni.hr.szabi.repository.queries;

import hu.webuni.hr.szabi.model.Company;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecs {


    public static Specification<Company> isLongTermCustomer() {
        return (root, query, builder) -> {

       //     return builder.lessThan(root.get(Company.c), date);
       return null;
        };
    }

}