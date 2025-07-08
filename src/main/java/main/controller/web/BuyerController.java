package main.controller.web;

import main.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Redirect to home for consistency
        return "redirect:/buyer/home";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("name", user.getName());
        model.addAttribute("totalOrders", 3);
        model.addAttribute("cartItems", 2);
        model.addAttribute("pendingReviews", 1);
        
        return "buyer/home";
    }

    @GetMapping("/orders")
    public String orders(HttpSession session, Model model, 
                        @RequestParam(value = "status", required = false) String status) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "buyer/orders";
    }

    @GetMapping("/wishlist")
    public String wishlist(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "buyer/wishlist";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        return "buyer/profile";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "buyer/cart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        return "buyer/checkout";
    }
}
