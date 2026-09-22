package myProject.Traineeship_repository;

import myProject.TraineeshipApp_domain.Company;
import myProject.TraineeshipApp_domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByCompany(Company company);
    

}
