package myProject.Traineeship_service;

import myProject.TraineeshipApp_domain.LogEntry;
import myProject.TraineeshipApp_domain.Student;
import myProject.Traineeship_repository.LogEntryRepository;
import myProject.Traineeship_repository.PositionRepository;
import myProject.Traineeship_repository.StudentRepository;

import java.util.List;

import myProject.TraineeshipApp_domain.Position;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // Εισαγωγή του StudentRepository μέσω του constructor
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Εύρεση μαθητή με βάση το username
    @Override
    public Student findByUsername(String username) {
        return studentRepository.findByUserUsername(username);
    }

    // Αποθήκευση μαθητή στη βάση δεδομένων
    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }
    
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private LogEntryRepository logEntryRepository;

    
    @Override
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public List<LogEntry> getLogEntriesForStudent(Long studentId) {
        return logEntryRepository.findByStudentId(studentId);
    }
    
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    
    public List<Student> findAllWithApplication() {
        return studentRepository.findByHasAppliedTrue();
    }

}
