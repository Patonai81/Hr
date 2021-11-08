package hu.webuni.hr.szabi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CompanyTypeFromDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Exclude private Long id;

    @NonNull
    private String companyForm;

   }
