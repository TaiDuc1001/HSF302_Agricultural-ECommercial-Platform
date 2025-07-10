package main.controller;

import main.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        
        return "public/home";
    }

    @GetMapping("/products")
    public String products(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "public/products";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable String id, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("productId", id);
        return "public/product-detail";
    }

    @GetMapping("/sellers")
    public String sellers(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "public/sellers";
    }

    @GetMapping("/sellers/{name}")
    public String sellerProfile(@PathVariable String name, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("sellerName", name);
        return "public/seller-profile";
    }
}
