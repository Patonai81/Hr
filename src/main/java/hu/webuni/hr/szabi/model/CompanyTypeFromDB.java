package hu.webuni.hr.szabi.model;

import javax.persistence.*;

@Entity
public class CompanyTypeFromDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    String companyForm;

}
