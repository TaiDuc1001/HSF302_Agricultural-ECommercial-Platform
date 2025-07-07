package main.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import main.enumerators.Role;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;
    String name;
    String email;
    String password;
    String address;
    String phoneNumber;
    Role role;
    Double totalSpent;
}
