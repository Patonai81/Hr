package hu.webuni.hr.szabi.mapper;

import hu.webuni.hr.szabi.dto.CompanyDto;
import hu.webuni.hr.szabi.model.Company;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface CompanyMapper {

    List<CompanyDto> toCompanyDtoList(List<Company> companyList);

    @IterableMapping( qualifiedByName = "toCompanyDtoWithout")
    List<CompanyDto> toCompanyDtoListWithoutEmployee(List<Company> companyList);


    List<Company> toCompanies(List<CompanyDto> companyDtoList);

    CompanyDto toCompanyDto(Company company);

    @Named("toCompanyDtoWithout")
    @Mapping( target = "employeesList", ignore = true)
    CompanyDto toCompanyDtoWithout(Company company);

    Company toCompany(CompanyDto companyDto);
}
