package main.controller;

import lombok.RequiredArgsConstructor;
import main.enumerators.Role;
import main.entities.User;
import main.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @GetMapping("/role/{stringRole}")
    public List<User> getUsersByRole(@PathVariable String stringRole) {
        Role role = Role.valueOf(stringRole.toUpperCase());
        return userService.getUsersByRole(role);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
