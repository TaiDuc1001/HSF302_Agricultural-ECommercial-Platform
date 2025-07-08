package main.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import main.enumerators.Role;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String email;
    String password;
    String address;
    String phone;
    Boolean isActive;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user")
    List<UserItem> userItems;

    @OneToMany(mappedBy = "user")
    List<Review> reviews;
}
