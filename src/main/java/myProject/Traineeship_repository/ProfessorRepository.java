package myProject.Traineeship_repository;

import myProject.TraineeshipApp_domain.Professor;
import myProject.TraineeshipApp_domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findByUserUsername(String username); // assuming User has username field
    
}
