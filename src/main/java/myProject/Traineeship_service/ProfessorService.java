package myProject.Traineeship_service;

import myProject.TraineeshipApp_domain.Professor;

public interface ProfessorService {
    void save(Professor professor);
    Professor findByUsername(String username);
}
