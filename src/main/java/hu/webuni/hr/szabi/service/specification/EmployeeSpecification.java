package hu.webuni.hr.szabi.service.specification;

import hu.webuni.hr.szabi.model.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeSpecification {

    public static Specification<Employee> hasId(Long id) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Employee_.id), id));
    }

    public static Specification<Employee> hasName(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
          return  criteriaBuilder.like(criteriaBuilder.lower(root.get(Employee_.employeeName)),"%"+name.toLowerCase()+"%");
        });
    }

    public static Specification<Employee> hasPosition(String position) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Employee_.position).get(Position_.positionName),position);
        });
    }

    public static Specification<Employee> hasSalary(Integer salary) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.between(root.get(Employee_.salary),(int)(salary*0.95),(int)(salary*1.05));
        });
    }

    public static Specification<Employee> hasStartWork(LocalDateTime startWork) {
       LocalDate startWorkDay = startWork.toLocalDate();

        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Employee_.startWork).as(LocalDate.class),startWorkDay);
        });
    }

    public static Specification<Employee> hasCompanyWorkFor(Company companyWorkFor) {
        String companyNameStr = companyWorkFor.getName();

        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get(Employee_.companyToWorkFor).get(Company_.name)),companyNameStr.toLowerCase()+"%");
        });
    }



}


