package hu.webuni.hr.szabi.model;


import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Exclude private Long id;

    @NonNull
    private String positionName;

    @Enumerated(EnumType.STRING)
    @NonNull
    private EducationType minimum_education_required;

    @NonNull
    private Long salary_min;

    //Több ember is lehet ugyanabba a szerepkörben
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private List<Employee> employeesInPosition;

    //Az adott pozíció közös a cégek között
    @ManyToOne
    private Company company;

    public void addEmployee(Employee employee){
        employeesInPosition.add(employee);
        employee.setPosition(this);
    }

    public void removeEmployee (Employee employee){
        employeesInPosition.remove(employee);
        employee.setPosition(null);
    }

}
