package main.controller;

import main.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        
        model.addAttribute("totalUsers", 150);
        model.addAttribute("totalProducts", 75);
        model.addAttribute("totalOrders", 320);
        model.addAttribute("activeSellers", 25);

        return "admin/home";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Redirect to home for consistency
        return "redirect:/admin/home";
    }

    @GetMapping("/users")
    public String users(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "admin/users";
    }

    @GetMapping("/products")
    public String products(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "admin/products";
    }

    @GetMapping("/orders")
    public String orders(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "admin/orders";
    }

    @GetMapping("/sellers")
    public String sellers(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "admin/sellers";
    }
}
