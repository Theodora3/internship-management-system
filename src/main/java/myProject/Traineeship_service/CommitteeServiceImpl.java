package myProject.Traineeship_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import myProject.TraineeshipApp_domain.CommitteeMember;
import myProject.Traineeship_repository.CommitteeRepository;

@Service
public class CommitteeServiceImpl implements CommitteeService {

    @Autowired
    private CommitteeRepository committeeRepository;

    @Override
    public CommitteeMember findByUsername(String username) {
        return committeeRepository.findByUserUsername(username);
    }
}
