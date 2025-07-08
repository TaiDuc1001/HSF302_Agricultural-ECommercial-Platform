package main.repository;

import main.enumerators.Role;
import main.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);
    List<User> findUsersByRole(Role role);
}
