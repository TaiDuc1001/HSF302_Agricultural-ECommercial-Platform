package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.UserDTO;
import main.entities.User;
import main.enumerators.Role;
import main.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @PostMapping
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/role/{stringRole}")
    public List<UserDTO> getUsersByRole(@PathVariable String stringRole) {
        Role role = Role.valueOf(stringRole.toUpperCase());
        return userService.getUsersByRole(role);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
