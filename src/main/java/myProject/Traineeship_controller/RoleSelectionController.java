package myProject.Traineeship_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleSelectionController {

    @GetMapping("/role-select")
    public String selectRoleForm() {
        return "role-select"; // Επιστρέφει τη φόρμα επιλογής ρόλου
    }

    @PostMapping("/register/redirect")
    public String handleRoleSelection(@RequestParam("role") String role) {
    	System.out.println("Επιλέχθηκε ρόλος: " + role);

        // Κατευθύνει στη σωστή φόρμα εγγραφής ανάλογα με το ρόλο
        switch (role) {
            case "student":
                return "redirect:/register-student"; // Ανακατεύθυνση στη φόρμα εγγραφής φοιτητή
            case "professor":
                return "redirect:/register-professor"; // Ανακατεύθυνση στη φόρμα εγγραφής καθηγητή
            case "company":
                return "redirect:/register-company"; // Ανακατεύθυνση στη φόρμα εγγραφής εταιρείας
            case "committee":
                return "redirect:/register-committee"; // Ανακατεύθυνση στη φόρμα εγγραφής επιτροπής
            default:
                return "redirect:/role-select"; // Αν δεν επιλεγεί ρόλος, επιστρέφει στην επιλογή ρόλου
        }
    }
}

