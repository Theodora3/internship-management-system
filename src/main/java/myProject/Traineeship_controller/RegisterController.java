
package myProject.Traineeship_controller;

import myProject.TraineeshipApp_domain.Student;
import myProject.TraineeshipApp_domain.Professor;
import myProject.TraineeshipApp_domain.User;
import myProject.Traineeship_service.StudentService;
import myProject.Traineeship_service.ProfessorService;
import myProject.Traineeship_service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import myProject.TraineeshipApp_domain.Company;
import myProject.Traineeship_service.CompanyService;
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfessorService professorService;

    // ----------------------- STUDENT -----------------------

    @GetMapping("/register-student")
    public String showStudentRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "register-student";
    }

    @PostMapping("/register-student")
    public String registerStudent(@ModelAttribute("student") Student student,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  Model model) {

        if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
            model.addAttribute("error", "Το πεδίο 'First Name' είναι υποχρεωτικό!");
            return "register-student";  // Επιστροφή στη φόρμα εγγραφής
        }

        if (!student.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Οι κωδικοί δεν ταιριάζουν!");
            return "register-student";  // Επιστροφή στη φόρμα εγγραφής
        }

        User user = new User();
        user.setEmail(student.getEmail());
        user.setPassword(student.getPassword());
        user.setRole("student");
        user.setFullName();
        user.setUsername(student.getUsername()); // ✔ αυτό είναι το σωστό!
        user.setFirstName(student.getFirstName());
        user.setLastName(student.getLastName());

        userService.createUser(user);


        student.setUser(user);         // Σύνδεση χρήστη με φοιτητή
        studentService.save(student);  // Αποθήκευση φοιτητή

     // Αν θες να δεις αμέσως το student dashboard (χωρίς login)
        return "login"; // χωρίς redirect!
    }


 // ---- PROFESSOR ----
    @GetMapping("/register-professor")
    public String showProfessorForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "register-professor";
    }


    @PostMapping("/register-professor")
    public String registerProfessor(@ModelAttribute("professor") Professor professor,
                                    @RequestParam("confirmPassword") String confirmPassword,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    @RequestParam("interestsInput") String interestsInput,
                                    Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Οι κωδικοί δεν ταιριάζουν!");
            return "register-professor";
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(professor.getUsername());
        user.setFirstName(professor.getFirstName());
        user.setLastName(professor.getLastName());
        user.setFullName();
        user.setPassword(password);
        user.setRole("professor");

        userService.createUser(user);

        professor.setUser(user);             // Σύνδεση χρήστη με καθηγητή
     
        professor.setInterests(interestsInput.trim());
        professorService.save(professor);    // Αποθήκευση καθηγητή

        return "login";

        
    }
    
    @Autowired
    private CompanyService companyService;

    

    @Autowired
    private PasswordEncoder passwordEncoder;

    // GET - Εμφάνιση φόρμας εγγραφής εταιρείας
    @GetMapping("/register-company")
    public String showCompanyRegisterForm(Model model) {
        model.addAttribute("company", new Company());
        return "register-company"; // templates/register-company.html
    }

    // POST - Επεξεργασία εγγραφής
    @PostMapping("/register-company")
    public String registerCompany(
            @ModelAttribute("company") Company company,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        if (!company.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Οι κωδικοί δεν ταιριάζουν!");
            return "register-company";
        }

        // Δημιουργία χρήστη
        User user = new User();
        user.setUsername(company.getUsername());
        user.setPassword(passwordEncoder.encode(company.getPassword()));
        user.setEmail(company.getEmail());
        user.setFirstName(company.getFirstName());
        user.setLastName(company.getLastName());
        user.setFullName();
        user.setRole("company"); // assuming you store it as String

        // Αποθήκευση του χρήστη
        //userService.createUser(user);

        // Σύνδεση χρήστη με την εταιρεία
        company.setUser(user);

        // Αποθήκευση εταιρείας
        companyService.save(company);

        return "login"; // Επιστροφή στη σελίδα login
    }

        
     
       




}

