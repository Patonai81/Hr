package hu.webuni.hr.szabi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Employee")
@Table(name = "employee")
@NamedQuery(name = "Employee.whatIs", query = "SELECT  e from Employee e where e.position.positionName like '%Wo%' ")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Employee {

    /**
     * Internal identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    Long id;

    /**
     * Name of employee
     */

    @EqualsAndHashCode.Exclude
    String employeeName;

    /**
     * Current salarí of employee
     */

    @EqualsAndHashCode.Exclude
    Integer salary;

    /**
     * Time of employee entry
     */

    @EqualsAndHashCode.Exclude
    public LocalDateTime startWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    Company companyToWorkFor;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    Position position;

    public Employee(String employeeName, int salary, LocalDateTime startWork, Company company, Position position) {
        this.employeeName=employeeName;
        this.salary=salary;
        this.startWork=startWork;
        this.companyToWorkFor=company;
        this.position=position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", startWork=" + startWork +
                '}';
    }
}
