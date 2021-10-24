package hu.webuni.hr.szabi.dto;

import java.util.List;

public class CompanyDto {

    Integer companyId;
    String name;
    String address;
    List<EmployeeDto> employeesList;

    public CompanyDto( CompanyDto input ) {
        this (input.companyId,input.name,input.address,null);
    }

    public CompanyDto(Integer companyId, String name, String address, List<EmployeeDto> employeesList) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;
        this.employeesList = employeesList;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
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
