package hu.webuni.hr.szabi.repository.result;

import java.io.Serializable;

public class CompanyBYAVGSalaryResult implements Serializable {

    private String companyName;
    private String employeeAssignment;
    private Double avgSalary;

    public CompanyBYAVGSalaryResult(String companyName, String employeeAssignment, Double avgSalary) {
        this.companyName = companyName;
        this.employeeAssignment = employeeAssignment;
        this.avgSalary = avgSalary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmployeeAssignment() {
        return employeeAssignment;
    }

    public void setEmployeeAssignment(String employeeAssignment) {
        this.employeeAssignment = employeeAssignment;
    }

    public Double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }
}
