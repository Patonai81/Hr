package hu.webuni.hr.szabi.model;

import hu.webuni.hr.szabi.validation.CompanyTypeDB;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Company")
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long companyId;

    String name;
    String address;

    @Enumerated(EnumType.STRING)
    CompanyType companyType;

    @ManyToOne(cascade = CascadeType.ALL)
    CompanyTypeFromDB companyTypeFromDB;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "companyToWorkFor")
    List<Employee> employeesList;

    public Company() {
    }

    public Company(Company input) {
        this(input.name, input.address, null, input.companyType, input.companyTypeFromDB);
    }

    @CompanyTypeDB
    public Company(String name, String address, List<Employee> employeesList, CompanyType companyType,CompanyTypeFromDB companyTypeFromDB) {
        this.name = name;
        this.address = address;
        this.companyType= companyType;
        this.employeesList = employeesList;
        this.companyTypeFromDB=companyTypeFromDB;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public void addEmployee(Employee employee){
        employeesList.add(employee);
        employee.setCompanyToWorkFor(this);
    }

    public void removeEmployee (Employee employee){
        employeesList.remove(employee);
        employee.setCompanyToWorkFor(null);
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public CompanyTypeFromDB getCompanyTypeFromDB() {
        return companyTypeFromDB;
    }

    public void setCompanyTypeFromDB(CompanyTypeFromDB companyTypeFromDB) {
        this.companyTypeFromDB = companyTypeFromDB;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", employeesList=" + employeesList +
                '}';
    }
}

