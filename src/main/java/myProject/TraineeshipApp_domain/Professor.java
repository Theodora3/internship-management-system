package myProject.TraineeshipApp_domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(name = "interests")
    private String interests; // Απλό πεδίο τύπου String
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String fullName;
    
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

   
    // Getters and Setters

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

    

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lasttName) {
        this.lastName = lasttName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @PrePersist
    public void setFullName() {
        this.fullName = this.firstName + " " + this.lastName;
    }
}
