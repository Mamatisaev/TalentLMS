package talent.service;

import talent.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    void insertCompany(Company company);

    Company getCompanyById(Long companyId);

    void updateCompany(Long companyId, Company company);

    void deleteCompany(Long companyId);

}
