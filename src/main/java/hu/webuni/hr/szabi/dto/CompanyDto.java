package hu.webuni.hr.szabi.dto;

import hu.webuni.hr.szabi.model.CompanyType;
import hu.webuni.hr.szabi.model.CompanyTypeFromDB;
import hu.webuni.hr.szabi.validation.CompanyTypeDB;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    Integer companyId;
    @NonNull
    String name;
    @NonNull
    String address;
    @NonNull
    List<EmployeeDto> employeesList;
    @NonNull
    CompanyType companyType;

    @CompanyTypeDB
    @NonNull
    CompanyTypeFromDB companyTypeFromDB;


    public CompanyDto(CompanyDto input ) {
        this (input.name,input.address,null,input.companyType,input.companyTypeFromDB);
    }



}
