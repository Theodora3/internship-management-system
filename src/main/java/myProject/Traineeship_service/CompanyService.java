package myProject.Traineeship_service;

import myProject.TraineeshipApp_domain.Company;

public interface CompanyService {
    void save(Company company);
    Company findByUsername(String username);
}
