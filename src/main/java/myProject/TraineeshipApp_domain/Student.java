package myProject.TraineeshipApp_domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Νέα πεδία για firstName και lastName
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String registrationNumber;
    
    


    @Column(length = 1000)  // ή μεγαλύτερο μήκος αν θες
    private String interests; // Interests ως String

    @Column(length = 1000)
    private String skills; // Skills ως String


    @Column(nullable = false)
    private String preferredLocation;
    public String getPreferredLocation() {
        return preferredLocation;
    }
    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    @Column(nullable = false)
    private boolean hasApplied = false;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "student_logbook", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "log_entry")
    private List<String> logbook = new ArrayList<>();

    // Νέα πεδία για email και password
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }


    public List<String> getLogbook() {
        return logbook;
    }

    public void setLogbook(List<String> logbook) {
        this.logbook = logbook != null ? logbook : new ArrayList<>();
    }

    public boolean isHasApplied() {
        return hasApplied;
    }

    public void setHasApplied(boolean hasApplied) {
        this.hasApplied = hasApplied;
    }

    // Προαιρετικά: Μπορείς να προσθέσεις getters/setters για τα νέα πεδία `firstName`, `lastName`, `email` και `password` εάν χρειάζεται.
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        // Μπορείς να υπολογίζεις το `fullName` συνδυάζοντας τα `firstName` και `lastName`
        return firstName + " " + lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
