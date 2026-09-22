package myProject.Traineeship_service;

import myProject.TraineeshipApp_domain.Company;
import myProject.TraineeshipApp_domain.Student;
import myProject.TraineeshipApp_domain.TraineeshipPosition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public interface TraineeshipPositionService {
	
	

	TraineeshipPosition findById(Long id);
    void assignToStudent(Long positionId, Student student);
    void save(TraineeshipPosition position);
    List<TraineeshipPosition> findByCompany(Company company);
    List<TraineeshipPosition> findAvailableByCompany(Company company);
    
    List<TraineeshipPosition> findUnassignedPositions();
    List<TraineeshipPosition> findAll();
    void applyStudentToPosition(Long positionId, Student student);
    List<TraineeshipPosition> findAllAvailablePositions();
    List<TraineeshipPosition> findAllWithApplicants();
    TraineeshipPosition findWithApplicantsById(Long id); 







}

