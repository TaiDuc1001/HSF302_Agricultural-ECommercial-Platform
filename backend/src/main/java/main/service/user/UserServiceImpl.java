package main.service.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.dto.UserDTO;
import main.entities.User;
import main.enumerators.Role;
import main.mapper.UserMapper;
import main.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO register(String email, String password, Role role) {
        User existingUser = userRepository.findUserByEmail(email);
        if (existingUser != null) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        
        User newUser = User.builder()
                .email(email)
                .password(password)
                .role(role)
                .isActive(true)
                .build();
        
        User savedUser = userRepository.save(newUser);
        return toDTO(savedUser);
    }

    @Override
    public UserDTO login(String email, String password) {
        User user = userRepository.findUserByEmailAndPassword(email, password);
        if(user == null){
            throw new EntityNotFoundException("User not found");
        }
        return toDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setIsActive(true);
        return toDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return toDTOs(userRepository.findAll());
    }

    @Override
    public List<UserDTO> getUsersByRole(Role role) {
        return toDTOs(userRepository.findUsersByRole(role));
    }

    @Override
    public UserDTO toDTO(User user) {
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> toDTOs(List<User> users) {
        return userMapper.toDTOs(users);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return toDTO(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id)));
    }
}
