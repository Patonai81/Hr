package hu.webuni.hr.szabi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Employee")
@Table(name = "employee")
@NamedQuery(name = "Employee.whatIs", query = "SELECT  e from Employee e where e.position.positionName like '%Wo%' ")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Employee {

    /**
     * Internal identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    Long id;

    /**
     * Name of employee
     */
    @NonNull
    String employeeName;

    /**
     * Current salarí of employee
     */
    @NonNull
    Integer salary;

    /**
     * Time of employee entry
     */
    @NonNull
    public LocalDateTime startWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    @EqualsAndHashCode.Exclude
    Company companyToWorkFor;

    @ManyToOne
    @NonNull
    @EqualsAndHashCode.Exclude
    Position position;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", startWork=" + startWork +
                '}';
    }
}
