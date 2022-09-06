package talent.repositoryImpl;

import org.springframework.stereotype.Repository;
import talent.entity.Company;
import talent.repository.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = entityManager.createQuery("SELECT c FROM Company c", Company.class).getResultList();
        Comparator<Company> comparator = ((o1, o2) -> (int) (o1.getCompanyId() - o2.getCompanyId()));
        companies.sort(comparator);
        return companies;
    }

    @Override
    public void insertCompany(Company company) {
        entityManager.persist(company);
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return entityManager.find(Company.class, companyId);
    }

    @Override
    public void updateCompany(Long companyId, Company company) {
        Company company1 = entityManager.find(Company.class, companyId);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        entityManager.merge(company1);
    }

    @Override
    public void deleteCompany(Long companyId) {
        entityManager.remove(entityManager.find(Company.class, companyId));
    }
}
