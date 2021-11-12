package hu.webuni.hr.szabi.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class PositionRelatedToCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private Long salary_min;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @NonNull
    private Position position;


    @ManyToOne
    @EqualsAndHashCode.Exclude
    @NonNull
    private Company company;


}
