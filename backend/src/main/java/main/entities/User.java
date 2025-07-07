package main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import main.enumerators.Role;

import java.util.List;

@Entity
@Table(name = "user")
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
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    Role role;
    Double totalSpent;

    @OneToMany(mappedBy = "user")
    List<UserItem> userItems;
}
