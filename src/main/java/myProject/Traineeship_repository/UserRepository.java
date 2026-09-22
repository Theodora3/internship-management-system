package myProject.Traineeship_repository;

import myProject.TraineeshipApp_domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Εδώ μπορούμε να προσθέσουμε custom queries αν χρειάζεται
    Optional<User> findByUsername(String username);
}
