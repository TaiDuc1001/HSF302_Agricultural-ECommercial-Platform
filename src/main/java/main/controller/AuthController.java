package main.controller;

import main.dto.UserDTO;
import main.entities.User;
import main.enumerators.Role;
import main.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, 
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                       @RequestParam String password,
                       HttpSession session,
                        RedirectAttributes redirectAttributes) {
        List<UserDTO> users = userService.getAllUsers();
        UserDTO user = null;
        for (UserDTO u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            redirectAttributes.addAttribute("error", "Invalid email or password");
            return "redirect:/auth/login";
        }
        
        session.setAttribute("user", user);
        session.setAttribute("name", user.getName());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole().toString());

        String role = user.getRole().toString();

        switch (role.toUpperCase()) {
            case "MANAGER":
                return "redirect:/manager/home";
            case "SELLER":
                return "redirect:/seller/home";
            case "BUYER":
                return "redirect:/buyer/home";
            default:
                return "redirect:/public/home";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String role,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        try {
            Role userRole = Role.valueOf(role.toUpperCase());
            UserDTO newUser = userService.register(email, password, userRole);
            
            session.setAttribute("user", newUser);
            session.setAttribute("email", newUser.getEmail());
            session.setAttribute("role", newUser.getRole().toString());
            session.setAttribute("name", newUser.getName());

            switch (userRole) {
                case MANAGER:
                    return "redirect:/manager/home";
                case SELLER:
                    return "redirect:/seller/home";
                case BUYER:
                    return "redirect:/buyer/home";
                default:
                    return "redirect:/public/home";
            }
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", "Registration failed: " + e.getMessage());
            return "redirect:/auth/register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "You have been logged out successfully");
        return "redirect:/auth/login";
    }

}
