package myProject.TraineeshipApp_domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "traineeship_positions")
public class TraineeshipPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String duration;
    private String topics_of_interest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(nullable = false)
    private boolean assigned = false;

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
    
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student assignedStudent;

    public Student getAssignedStudent() {
        return assignedStudent;
    }

    public void setAssignedStudent(Student assignedStudent) {
        this.assignedStudent = assignedStudent;
    }

    @ManyToMany
    @JoinTable(
        name = "position_applications",
        joinColumns = @JoinColumn(name = "position_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> applicants = new ArrayList<>();

    // Getter και Setter
    public List<Student> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Student> applicants) {
        this.applicants = applicants;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    @Column(length = 1000)

    private String requiredSkills;

    public String getRequiredSkills() {
        return requiredSkills;

    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;

    }
    
    public String getTopics_of_interest() {
        return topics_of_interest;

    }

    public void setTopics_of_interest(String topics_of_interest) {
        this.topics_of_interest = topics_of_interest;

    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

