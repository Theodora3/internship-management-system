package myProject.Traineeship_controller;

import myProject.TraineeshipApp_domain.Professor;
import myProject.Traineeship_service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    
   


    @GetMapping("/professor_dashboard")
    public String showProfessorDashboard(Model model, Principal principal) {
        Professor professor = professorService.findByUsername(principal.getName());
        model.addAttribute("professor", professor);
        
        return "professor_dashboard";
    }

}
