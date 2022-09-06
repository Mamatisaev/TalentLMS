package talent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talent.entity.Company;
import talent.repository.CompanyRepository;
import talent.service.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    @Override
    public void insertCompany(Company company) {
        companyRepository.insertCompany(company);
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.getCompanyById(companyId);
    }

    @Override
    public void updateCompany(Long companyId, Company company) {
        companyRepository.updateCompany(companyId, company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteCompany(id);
    }
}