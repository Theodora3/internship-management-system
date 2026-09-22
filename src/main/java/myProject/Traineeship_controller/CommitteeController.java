package myProject.Traineeship_controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import myProject.TraineeshipApp_domain.CommitteeMember;
import myProject.TraineeshipApp_domain.Student;
import myProject.TraineeshipApp_domain.TraineeshipPosition;
import myProject.Traineeship_service.CommitteeService;
import myProject.Traineeship_service.StudentService;
import myProject.Traineeship_service.TraineeshipPositionService;

@Controller
public class CommitteeController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private TraineeshipPositionService traineeshipPositionService;


    /*@GetMapping("/committee_assigned_positions")
    public String showAssignedPositions() {
        return "company_available_positions";
    }*/
    
    @GetMapping("/company_available_positions")
    public String showAvailablePositionsForCommittee(Model model) {
        List<TraineeshipPosition> positions = traineeshipPositionService.findAllAvailablePositions();
        model.addAttribute("positions", positions);
        return "company_available_positions";
    }


    
    @Autowired
    private CommitteeService committeeService;

    @GetMapping("/committee_dashboard")
    public String showDashboard(Model model, Principal principal) {
        System.out.println(">>> Committee Dashboard ενεργοποιήθηκε");
        CommitteeMember committee = committeeService.findByUsername(principal.getName());
        model.addAttribute("committee", committee);
        return "committee_dashboard";
    }
    
    @GetMapping("/committee_students")
    public String showStudents(Model model) {
        List<Student> appliedStudents = studentService.findAllWithApplication();
        model.addAttribute("students", appliedStudents);
        return "committee_students";
    }

    

    public CommitteeController(TraineeshipPositionService traineeshipPositionService) {
        this.traineeshipPositionService = traineeshipPositionService;
    }

    @GetMapping("/committee_position_applicants")
    public String viewAllApplications(Model model) {
        List<TraineeshipPosition> positions = traineeshipPositionService.findAllWithApplicants();
        model.addAttribute("positions", positions);
        return "committee_position_applicants";  // το όνομα του HTML view
    }



}


