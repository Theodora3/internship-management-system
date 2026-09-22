package myProject.TraineeshipApp_domain;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "positions")
@Getter
@Setter
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student assignedStudent;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id") // ή ό,τι foreign key υπάρχει στη βάση σου
    private Company company;



    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    public Student getAssignedStudent() {
        return assignedStudent;
    }

    public void setAssignedStudent(Student assignedStudent) {
        this.assignedStudent = assignedStudent;
    }

}
