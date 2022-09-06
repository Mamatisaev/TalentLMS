package talent.repository;

import talent.entity.Company;

import java.util.List;

public interface CompanyRepository {

    void insertCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompanyById(Long companyId);

    void updateCompany(Long companyId, Company company);

    void deleteCompany(Long id);

}