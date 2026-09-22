package myProject.Traineeship_service;

import myProject.TraineeshipApp_domain.Company;
import myProject.Traineeship_repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company findByUsername(String username) {
        return companyRepository.findByUserUsername(username);
    }
}
