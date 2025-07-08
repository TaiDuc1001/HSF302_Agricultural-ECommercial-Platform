package main.controller.web;

import main.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("name", user.getName());
        
        model.addAttribute("totalProducts", 12);
        model.addAttribute("totalOrders", 156);
        model.addAttribute("totalRevenue", 15000.00);
        model.addAttribute("pendingOrders", 8);
        model.addAttribute("lastLogin", java.time.LocalDateTime.now().minusHours(2));
        
        return "seller/home";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Redirect to home for consistency
        return "redirect:/seller/home";
    }

    @GetMapping("/products")
    public String products(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "seller/products";
    }

    @GetMapping("/orders")
    public String orders(HttpSession session, Model model,
                        @RequestParam(value = "status", required = false) String status) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("totalOrders", 156);
        model.addAttribute("pendingOrders", 8);
        model.addAttribute("shippedOrders", 15);
        model.addAttribute("totalRevenue", 12500.00);
        
        return "seller/orders";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        return "seller/profile";
    }

    @GetMapping("/add-product")
    public String addProduct(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "seller/add-product";
    }
}
