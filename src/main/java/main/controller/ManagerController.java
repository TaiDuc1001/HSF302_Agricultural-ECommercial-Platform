package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.UserDTO;
import main.enumerators.Role;
import main.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final UserService userService;
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("name", user.getName());
        model.addAttribute("totalUsers", 150);
        model.addAttribute("totalProducts", 75);
        model.addAttribute("totalOrders", 320);
        model.addAttribute("activeSellers", 25);

        return "manager/home";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        return "redirect:/manager/home";
    }

    @GetMapping("/users")
    public String users(HttpSession session, Model model,
                        @RequestParam(required = false) String role,
                        @RequestParam(required = false) String search) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        List<UserDTO> users;

        // Nếu có search term, search trước
        if (search != null && !search.trim().isEmpty()) {
            users = userService.searchUsers(search);
        }
        // Nếu có role filter và không phải empty (tức là không phải "All Roles")
        else if (role != null && !role.trim().isEmpty()) {
            try {
                users = userService.getUsersByRole(Role.valueOf(role.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Nếu role không hợp lệ, lấy tất cả users
                users = userService.getAllUsers();
            }
        }
        // Mặc định lấy tất cả users (khi role = "" hoặc null)
        else {
            users = userService.getAllUsers();
        }

        // Calculate statistics
        long totalUsers = userService.getAllUsers().size();
        long totalBuyers = userService.getUsersByRole(Role.BUYER).size();
        long totalSellers = userService.getUsersByRole(Role.SELLER).size();
        long totalManagers = userService.getUsersByRole(Role.MANAGER).size();

        model.addAttribute("name", user.getName());
        model.addAttribute("users", users);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalBuyers", totalBuyers);
        model.addAttribute("totalSellers", totalSellers);
        model.addAttribute("totalManagers", totalManagers);

        // Thêm current filters để giữ lại state
        model.addAttribute("currentRole", role);
        model.addAttribute("currentSearch", search);
        return "manager/users";
    }

    @PostMapping("/users/{userId}/toggle-status")
    @ResponseBody
    public Map<String, Object> toggleUserStatus(@PathVariable Long userId,
                                                @RequestBody Map<String, Boolean> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Boolean activate = request.get("activate");
            UserDTO updatedUser = userService.updateUserStatus(userId, activate);
            response.put("success", true);
            response.put("message", "User status updated successfully");
            response.put("user", updatedUser);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update user status: " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/users/{userId}")
    public String viewUser(@PathVariable Long userId, HttpSession session, Model model) {
        UserDTO currentUser = (UserDTO) session.getAttribute("user");
        if (currentUser == null || !"MANAGER".equals(currentUser.getRole().toString())) {
            return "redirect:/auth/login";
        }

        try {
            UserDTO user = userService.getUserById(userId);
            model.addAttribute("name", currentUser.getName());
            model.addAttribute("user", user);
            return "manager/user-detail";
        } catch (Exception e) {
            return "redirect:/manager/users";
        }
    }

    @GetMapping("/products")
    public String products(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "manager/products";
    }

    @GetMapping("/orders")
    public String orders(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "manager/orders";
    }

    @GetMapping("/sellers")
    public String sellers(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "manager/sellers";
    }
}
