package hu.webuni.hr.szabi.dto;

import java.util.List;

public class CompanyDto {

    String companyId;
    String name;
    String address;
    List<EmployeeDto> employeesList;

    public CompanyDto() {
    }

    public CompanyDto(String companyId, String name, String address, List<EmployeeDto> employeesList) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;
        this.employeesList = employeesList;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
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

    public List<EmployeeDto> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<EmployeeDto> employeesList) {
        this.employeesList = employeesList;
    }
}
