package hu.webuni.hr.szabi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EmployeeDto {


    /**
     * Internal identifier
     */
    Long id;

    /**
     * Name of employee
     */
    String employeeName;

    /**
     * Actual assignment of employee
     */
    String assignment;

    /**
     * Current salarí of employee
     */
    Integer salary;

    /**
     * Time of employee entry
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime startWork;

    public EmployeeDto() {
    }

    public EmployeeDto(EmployeeDto newEmp){
        this.employeeName = newEmp.employeeName;
        this.assignment = newEmp.assignment;
        this.salary = newEmp.salary;
        this.startWork = newEmp.startWork;
    }

    public EmployeeDto(Long id, String employeeName, String assignment, Integer salary, LocalDateTime startWork) {
        this.id = id;
        this.employeeName = employeeName;
        this.assignment = assignment;
        this.salary = salary;
        this.startWork = startWork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDateTime getStartWork() {
        return startWork;
    }

    public void setStartWork(LocalDateTime startWork) {
        this.startWork = startWork;
    }
}
