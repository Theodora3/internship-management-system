package myProject.Traineeship_repository;

import myProject.TraineeshipApp_domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByUserUsername(String username);
}
