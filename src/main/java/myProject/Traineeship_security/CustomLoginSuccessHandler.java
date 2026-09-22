package myProject.Traineeship_security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Εξαγωγή του ρόλου του χρήστη χωρίς το "ROLE_"
    	String role = authentication.getAuthorities().stream()
    		    .map(GrantedAuthority::getAuthority)
    		    .map(String::toLowerCase) 
    		    .collect(Collectors.joining());

    		System.out.println("🎭 Role (without 'ROLE_'): " + role);  // Έλεγχος του ρόλου χωρίς το "ROLE_"


        // Ανάλογα με τον ρόλο, ανακατευθύνει τον χρήστη στο κατάλληλο dashboard
        if ("student".equals(role)) {
            response.sendRedirect("/student_dashboard");
        } else if ("professor".equals(role)) {
            response.sendRedirect("/professor_dashboard");
        } else if ("company".equals(role)) {
            response.sendRedirect("/company_dashboard");
        } else if ("committee".equals(role)) {
            response.sendRedirect("/committee_dashboard");
        } else {
            // Αν δεν υπάρχει ο ρόλος, ανακατεύθυνση σε σελίδα πρόσβασης
            response.sendRedirect("/access-denied");
        }
    }
}
