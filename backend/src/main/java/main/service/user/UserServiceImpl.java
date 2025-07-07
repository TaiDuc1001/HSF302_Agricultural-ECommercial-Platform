package main.service.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.enumerators.Role;
import main.repository.UserRepository;
import main.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User login(String email, String password) {
        User user = userRepository.findUserByEmailAndPassword(email, password);
        if(user == null){
            throw new EntityNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findUsersByRole(role);
    }
}
