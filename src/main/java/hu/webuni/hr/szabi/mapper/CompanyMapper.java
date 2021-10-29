package hu.webuni.hr.szabi.mapper;

import hu.webuni.hr.szabi.dto.CompanyDto;
import hu.webuni.hr.szabi.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface CompanyMapper {

    List<CompanyDto> toCompanyDtoList(List<Company> companyList);

    List<Company> toCompanies(List<CompanyDto> companyDtoList);

    CompanyDto toCompanyDto(Company company);

    Company toCompany(CompanyDto companyDto);
}
