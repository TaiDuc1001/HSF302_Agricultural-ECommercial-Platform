package main.controller;

import main.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        // Forward to public home page
        return "forward:/public/home";
    }

    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        // Forward to public home page
        return "forward:/public/home";
    }
    
    @GetMapping("/content")
    public String content(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/auth/login";
        }
        
        switch (user.getRole()) {
            case ADMIN:
                return "redirect:/admin/home";
            case SELLER:
                return "redirect:/seller/home";
            case BUYER:
                return "redirect:/buyer/home";
            default:
                return "redirect:/";
        }
    }
}
