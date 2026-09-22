package myProject.Traineeship_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import myProject.TraineeshipApp_domain.Company;
import myProject.TraineeshipApp_domain.Student;
import myProject.TraineeshipApp_domain.TraineeshipPosition;
import myProject.Traineeship_repository.TraineeshipPositionRepository;

@Service
public class TraineeshipPositionServiceImpl implements TraineeshipPositionService {

    @Autowired
    private TraineeshipPositionRepository repository;

    @Override
    public void save(TraineeshipPosition position) {
        repository.save(position);
    }

    @Override
    public List<TraineeshipPosition> findByCompany(Company company) {
        return repository.findByCompany(company);
    }

    @Override
    public List<TraineeshipPosition> findAvailableByCompany(Company company) {
        return repository.findByAssignedFalseAndCompany(company);
    }

    @Override
    public List<TraineeshipPosition> findUnassignedPositions() {
        return repository.findByAssignedFalse();
    }

   

    @Override
    public List<TraineeshipPosition> findAll() {
        return repository.findAll();
    }
    
    @Override
    @Transactional
    public void applyStudentToPosition(Long positionId, Student student) {
        TraineeshipPosition position = repository.findWithApplicantsById(positionId)
            .orElseThrow(() -> new RuntimeException("Η θέση δεν βρέθηκε!"));

        if (!position.getApplicants().contains(student)) {
            position.getApplicants().add(student);
            repository.save(position);
        }
    }

    
    
    

    @Override
    public List<TraineeshipPosition> findAllAvailablePositions() {
        return repository.findByAssignedFalse();
    }

    
    @Override
    public List<TraineeshipPosition> findAllWithApplicants() {
        return repository.findAllWithApplicants();
    }

    @Override
    public TraineeshipPosition findWithApplicantsById(Long id) {
        return repository.findWithApplicantsById(id).orElse(null);
    }

    @Override
    public TraineeshipPosition findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void assignToStudent(Long positionId, Student student) {
        TraineeshipPosition position = findById(positionId);
        if (position != null && position.getAssignedStudent() == null) {
            position.setAssignedStudent(student);
            repository.save(position);
        }
    }
}

