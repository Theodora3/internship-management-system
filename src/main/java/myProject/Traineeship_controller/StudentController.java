package myProject.Traineeship_controller;

import myProject.TraineeshipApp_domain.Student;
import myProject.TraineeshipApp_domain.TraineeshipPosition;
import myProject.TraineeshipApp_domain.Position;
import myProject.Traineeship_service.StudentService;
import myProject.Traineeship_service.TraineeshipPositionService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import myProject.TraineeshipApp_domain.LogEntry;


import java.security.Principal;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private TraineeshipPositionService ΤraineeshipPositionService;
    
    @Autowired
    private TraineeshipPositionService traineeshipPositionService;



    @GetMapping("/student/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_register";
    }

    @GetMapping("/student/profile")
    public String showProfile(Model model, Principal principal) {
        Student student = studentService.findByUsername(principal.getName());
        model.addAttribute("student", student);
        return "student_profile";
    }

    @GetMapping("/student_edit_profile") // consistent with menu
    public String editProfile(Model model, Principal principal) {
        Student student = studentService.findByUsername(principal.getName());
        model.addAttribute("student", student);
        return "student_edit_profile";
    }

    

 // Μεταφορά στη φόρμα αίτησης πρακτικής άσκησης
    @GetMapping("/apply-for-traineeship")
    public String applyForTraineeship(Model model) {
        // Παράδειγμα: φορτώνουμε τις θέσεις πρακτικής άσκησης ή άλλες πληροφορίες
        model.addAttribute("positions", studentService.getAllPositions());
        return "applyForTraineeship"; // Επιστρέφει τη σελίδα της αίτησης
    }

 

    // Για να δείξει το logbook του φοιτητή
    @GetMapping("/logbook")
    public String showLogbook(Principal principal, Model model) {
        // Βρες τον φοιτητή με βάση το username του (από το session)
        Student student = studentService.findByUsername(principal.getName());

        List<LogEntry> logEntries = studentService.getLogEntriesForStudent(student.getId());
        model.addAttribute("logEntries", logEntries);

        return "logbook";  // student-logbook.html
    }
    

    
    @GetMapping("/student_dashboard")
    public String showDashboard(Model model, Principal principal) {
        Student student = studentService.findByUsername(principal.getName());
        if (student == null) {
            return "studcont";  // Μπορείς να προσθέσεις μια σελίδα σφάλματος για το student
        }
        model.addAttribute("student", student);
        return "student_dashboard";  // Η σελίδα student_dashboard.html θα πρέπει να υπάρχει στο φάκελο templates
    }
    
    @GetMapping("/student/available-positions")
    public String viewAvailablePositions(Model model) {
        List<TraineeshipPosition> availablePositions = ΤraineeshipPositionService.findUnassignedPositions(); // ΜΗΝ είναι static
        model.addAttribute("positions", availablePositions);
        return "student_available_positions";
    }

    
    @GetMapping("/student/apply-form")
    public String showApplyForm(@RequestParam("positionId") Long positionId, Principal principal, Model model) {
        Student student = studentService.findByUsername(principal.getName());
        TraineeshipPosition position = traineeshipPositionService.findById(positionId);

        model.addAttribute("student", student);
        model.addAttribute("position", position);
        return "applyForTraineeship"; // HTML σελίδα
    }

    

    @PostMapping("/student/submit-application")
    public String submitApplication(@RequestParam("positionId") Long positionId,
                                     Principal principal,
                                     RedirectAttributes redirectAttributes) {
        Student student = studentService.findByUsername(principal.getName());

        traineeshipPositionService.applyStudentToPosition(positionId, student);

        //student.setHasApplied(true);
        studentService.save(student);
        redirectAttributes.addFlashAttribute("successMessage", "Η αίτησή σας υποβλήθηκε με επιτυχία!");
        return "redirect:/student_dashboard";
    }


}

