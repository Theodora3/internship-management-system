package myProject.Traineeship_service;

import myProject.TraineeshipApp_domain.Company;
import myProject.TraineeshipApp_domain.Position;

import java.util.List;

public interface PositionService {
    List<Position> findByCompany(Company company);
    List<Position> findAssignedByCompany(Company company);
}
