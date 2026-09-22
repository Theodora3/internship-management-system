package myProject.Traineeship_service;

import myProject.TraineeshipApp_domain.Professor;
import myProject.Traineeship_repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public void save(Professor professor) {
        professorRepository.save(professor);
    }

    @Override
    public Professor findByUsername(String username) {
        return professorRepository.findByUserUsername(username);
    }
}

