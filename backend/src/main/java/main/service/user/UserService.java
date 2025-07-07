package main.service.user;

import main.dto.UserDTO;
import main.entities.User;
import main.enumerators.Role;

import java.util.List;

public interface UserService {
    public abstract UserDTO login(String email, String password);
    public abstract List<UserDTO> getAllUsers();
    public abstract UserDTO getUserById(Long id);
    public abstract List<UserDTO> getUsersByRole(Role role);
    public abstract UserDTO toDTO(User user);
    public abstract List<UserDTO> toDTOs(List<User> users);
    public abstract UserDTO createUser(UserDTO userDTO);
}
