package hu.webuni.hr.szabi.model;

import hu.webuni.hr.szabi.repository.PositionRepository;
import hu.webuni.hr.szabi.validation.CompanyTypeDB;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@NamedEntityGraph(name = "Company.companyWithEmployees", attributeNodes = {@NamedAttributeNode(value = "employeesList"), @NamedAttributeNode(value = "companyTypeFromDB")})
@Entity(name = "Company")
@Table(name = "company")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include Long companyId;

    @NonNull
    @EqualsAndHashCode.Exclude String name;

    @NonNull
    @EqualsAndHashCode.Exclude String address;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "companyToWorkFor")
    @NonNull
    @EqualsAndHashCode.Exclude List<Employee> employeesList;

    @Enumerated(EnumType.STRING)
    @NonNull
    @EqualsAndHashCode.Exclude CompanyType companyType;

    @ManyToOne
    @CompanyTypeDB
    @NonNull
    @EqualsAndHashCode.Exclude CompanyTypeFromDB companyTypeFromDB;



    public Company(Company input) {
        this(input.name, input.address,null, input.companyType, input.companyTypeFromDB);
    }

    public void addEmployee(Employee employee,Position position){
        employeesList.add(employee);
        employee.setCompanyToWorkFor(this);
        employee.setPosition(position);

    }
    public void removeEmployee (Employee employee){
        employeesList.remove(employee);
    }

    public void replaceEmployees (List<Employee> employeeList){
            this.employeesList.retainAll(employeeList);
            this.employeesList.addAll(employeeList);
    }


    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", companyType=" + companyType +
                ", companyTypeFromDB=" + companyTypeFromDB +
                '}';
    }


}

