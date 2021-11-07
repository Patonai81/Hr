package hu.webuni.hr.szabi.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Employee")
@Table(name = "employee")
@NamedQuery(name = "Employee.whatIs", query = "SELECT  e from Employee e where e.assignment like '%Wo%' ")
public class Employee {

    /**
     * Internal identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    public LocalDateTime startWork;

    @ManyToOne(fetch= FetchType.LAZY)
    Company companyToWorkFor;

    public Employee() {
    }

    public Employee(String employeeName, String assignment, Integer salary, LocalDateTime startWork, Company company) {
        this.employeeName = employeeName;
        this.assignment = assignment;
        this.salary = salary;
        this.startWork = startWork;
        this.companyToWorkFor=company;
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

    public void setEmployeeName(String emploeeName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public Company getCompanyToWorkFor() {
        return companyToWorkFor;
    }

    public void setCompanyToWorkFor(Company companyToWorkFor) {
        this.companyToWorkFor = companyToWorkFor;
    }

}
