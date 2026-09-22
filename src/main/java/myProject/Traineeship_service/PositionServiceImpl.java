package myProject.Traineeship_service;

import myProject.TraineeshipApp_domain.Company;
import myProject.TraineeshipApp_domain.Position;
import myProject.Traineeship_repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> findByCompany(Company company) {
        return positionRepository.findByCompany(company);
    }

    @Override
    public List<Position> findAssignedByCompany(Company company) {
        List<Position> all = positionRepository.findByCompany(company);
        return all.stream()
                  .filter(position -> position.getAssignedStudent() != null)
                  .collect(Collectors.toList());
    }
}
