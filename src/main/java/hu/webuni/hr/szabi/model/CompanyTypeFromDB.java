package hu.webuni.hr.szabi.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
public class CompanyTypeFromDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    String companyForm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyForm() {
        return companyForm;
    }

    public void setCompanyForm(String companyForm) {
        this.companyForm = companyForm;
    }

    public CompanyTypeFromDB(String companyForm) {
        this.companyForm = companyForm;
    }

    public CompanyTypeFromDB() {
    }

    @Override
    public String toString() {
        return "CompanyTypeFromDB{" +
                "companyForm='" + companyForm + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CompanyTypeFromDB that = (CompanyTypeFromDB) o;

        return new EqualsBuilder().append(companyForm, that.companyForm).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(companyForm).toHashCode();
    }
}
