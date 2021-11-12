package hu.webuni.hr.szabi.model;


import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NonNull
    private String positionName;

    @Enumerated(EnumType.STRING)
    @NonNull
    private EducationType minimum_education_required;

    @OneToMany(mappedBy = "position")
    @EqualsAndHashCode.Exclude
    private Set<Employee> employees;

}
