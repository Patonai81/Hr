package hu.webuni.hr.szabi.model;

import java.util.List;

public class Company {

    Integer companyId;
    String name;
    String address;
    List<Employee> employeesList;

    public Company() {
    }

    public Company(Company input) {
        this (input.companyId,input.name,input.address,null);
    }

    public Company(Integer companyId, String name, String address, List<Employee> employeesList) {
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

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }
}

