package main.service.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.dto.UserDTO;
import main.pojo.Order;
import main.pojo.Review;
import main.pojo.User;
import main.enumerators.Role;
import main.mapper.UserMapper;
import main.repository.OrderRepository;
import main.repository.ProduceRepository;
import main.repository.ReviewRepository;
import main.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProduceRepository produceRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

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
    @Override
    public UserDTO updateUserStatus(Long userId, Boolean isActive) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        user.setIsActive(isActive);
        return toDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> searchUsers(String searchTerm) {
        List<User> users = userRepository.findUsersByNameContainingOrEmailContaining(searchTerm, searchTerm);
        return toDTOs(users);
    }
    @Override
    public int countProductsBySellerId(Long sellerId) {
        return produceRepository.countByUser_Id(sellerId);
    }

    @Override
    public int countOrdersBySellerId(Long sellerId) {
        return orderRepository.countByUser_Id(sellerId);
    }

    @Override
    public double sumRevenueBySellerId(Long sellerId) {
        List<Order> orders = orderRepository.findAllByOrderDetails_Produce_User_Id(sellerId);
        double sum = 0.0;
        for (Order order : orders) {
            if (order.getFinalAmount() != null) {
                sum += order.getFinalAmount().doubleValue();
            }
        }
        return sum;
    }

    @Override
    public double averageRatingBySellerId(Long sellerId) {
        List<Review> reviews = reviewRepository.findAllByUser_Id(sellerId);
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }

    @Override
    public int countReviewsBySellerId(Long sellerId) {
        return reviewRepository.countByUser_Id(sellerId);
    }
}
