package main.service.user;

import main.dto.UserDTO;
import main.pojo.User;
import main.enumerators.Role;

import java.util.List;

public interface UserService {
    public abstract UserDTO login(String email, String password);
    public abstract UserDTO register(String email, String password, Role role);
    public abstract List<UserDTO> getAllUsers();
    public abstract UserDTO getUserById(Long id);
    public abstract List<UserDTO> getUsersByRole(Role role);
    public abstract UserDTO toDTO(User user);
    public abstract List<UserDTO> toDTOs(List<User> users);
    public abstract UserDTO createUser(UserDTO userDTO);
    public abstract UserDTO updateUserStatus(Long userId, Boolean isActive);
    public abstract List<UserDTO> searchUsers(String searchTerm);
    int countProductsBySellerId(Long sellerId);
    int countOrdersBySellerId(Long sellerId);
    double sumRevenueBySellerId(Long sellerId);
    double averageRatingBySellerId(Long sellerId);
    int countReviewsBySellerId(Long sellerId);
    public abstract User findByID(Long id);

}
