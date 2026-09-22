package myProject.Traineeship_repository;
import myProject.TraineeshipApp_domain.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findByUserUsername(String username);
	List<Student> findByHasAppliedTrue();
	 

}


