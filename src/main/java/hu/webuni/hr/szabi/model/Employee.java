package hu.webuni.hr.szabi.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Employee {

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
    LocalDateTime startWork;

    public Employee() {
    }

    public Employee(Long id, String employeeName, String assignment, Integer salary, LocalDateTime startWork) {
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

    public String getEmploeeName() {
        return employeeName;
    }

    public void setEmploeeName(String emploeeName) {
        this.employeeName = emploeeName;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", assignment='" + assignment + '\'' +
                ", salary=" + salary +
                ", startWork=" + startWork +
                '}';
    }
}
