package myProject.Traineeship_controller;

import myProject.TraineeshipApp_domain.Company;
import myProject.TraineeshipApp_domain.Position;
import myProject.TraineeshipApp_domain.TraineeshipPosition;
import myProject.TraineeshipApp_domain.User;
import myProject.Traineeship_service.CompanyService;
import myProject.Traineeship_service.PositionService;
import myProject.Traineeship_service.TraineeshipPositionService;
import myProject.Traineeship_service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PositionService positionService;
    
    @Autowired
    private TraineeshipPositionService traineeshipPositionService;


    @GetMapping("/company/profile")
    public String showProfile(Model model, Principal principal) {
        Company company = companyService.findByUsername(principal.getName());
        model.addAttribute("company", company);
        return "company_profile";
    }

    @GetMapping("/company/edit-profile")
    public String editProfile(Model model, Principal principal) {
        Company company = companyService.findByUsername(principal.getName());
        model.addAttribute("company", company);
        return "company_edit_profile";
    }

    @GetMapping("/company_dashboard")
    public String showDashboard(Model model, Principal principal) {
        Company company = companyService.findByUsername(principal.getName());
        if (company == null) {
            return "error";
        }
        model.addAttribute("company", company);
        return "company_dashboard";
    }

    @GetMapping("/company/positions")
    public String showAllPositions(Model model, Principal principal) {
        Company company = companyService.findByUsername(principal.getName());
        List<Position> positions = positionService.findByCompany(company);
        model.addAttribute("positions", positions);
        return "company_positions";  // templates/company_positions.html
    }

    @GetMapping("/company/assigned-positions")
    public String showAssignedPositions(Model model, Principal principal) {
        Company company = companyService.findByUsername(principal.getName());
        List<Position> assigned = positionService.findAssignedByCompany(company);
        model.addAttribute("positions", assigned);
        return "company_assigned_positions"; // templates/company_assigned_positions.html
    }
    
 /*   @GetMapping("/company_available_positions")
    public String showAvailablePositions(Model model, Principal principal) {
        // Βρες τον χρήστη (εταιρεία)
        Company company = companyService.findByUsername(principal.getName());

        // Φέρε τις ΘΕΣΕΙΣ που δεν έχουν ανατεθεί ακόμα
        List<TraineeshipPosition> availablePositions = traineeshipService.findAvailableByCompany(company);

        model.addAttribute("positions", availablePositions);
        return "company_available_positions";
    }*/
    
 // ✅ Προβολή φόρμας δημιουργίας θέσης
    @GetMapping("/company/create-position")
    public String showCreateForm(Model model) {
        model.addAttribute("traineeshipPosition", new TraineeshipPosition());
        return "create_traineeship_position";
    }
    @Autowired
    private UserService userService;

    // ✅ Καταχώρηση νέας θέσης
    @PostMapping("/company/create-position")
    public String createPosition(@ModelAttribute("position") TraineeshipPosition position, Principal principal) {
    	User user = userService.getUserByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));


    	Company company = companyService.findByUsername(user.getUsername());

        position.setCompany(company);
        traineeshipPositionService.save(position);

        return "redirect:/company_dashboard";
    }

    // 📄 Προβολή θέσεων που έχει καταχωρήσει η εταιρεία
    @GetMapping("/company/available_positions")
    public String showAvailablePositions(Model model, Principal principal) {
    	User user = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));

    	Company company = companyService.findByUsername(user.getUsername());

        List<TraineeshipPosition> positions = traineeshipPositionService.findAvailableByCompany(company);

        model.addAttribute("positions", positions);
        return "company_available_positions";
    }


}
