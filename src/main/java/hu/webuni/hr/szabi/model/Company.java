package hu.webuni.hr.szabi.model;

import hu.webuni.hr.szabi.validation.CompanyTypeDB;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Company")
@Table(name = "company")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude Long companyId;

    @NonNull
    String name;
    @NonNull
    String address;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "companyToWorkFor")
    @NonNull
    @EqualsAndHashCode.Exclude List<Employee> employeesList;

    @Enumerated(EnumType.STRING)
    @NonNull
    CompanyType companyType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @CompanyTypeDB
    @NonNull
    CompanyTypeFromDB companyTypeFromDB;

    public Company(Company input) {
        this(input.name, input.address,null, input.companyType, input.companyTypeFromDB);
    }

    public void addEmployee(Employee employee){
        employeesList.add(employee);
        employee.setCompanyToWorkFor(this);
    }

    public void removeEmployee (Employee employee){
        employeesList.remove(employee);
        employee.setCompanyToWorkFor(null);
    }

}

