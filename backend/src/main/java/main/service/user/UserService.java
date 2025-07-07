package main.service.user;

import main.enumerators.Role;
import main.entities.User;

import java.util.List;

public interface UserService {
    public abstract User login(String email, String password);
    public abstract List<User> getAllUsers();
    public abstract List<User> getUsersByRole(Role role);
}
