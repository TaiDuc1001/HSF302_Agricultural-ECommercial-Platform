package main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produce")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Produce {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;

    String name;
    String description;
    BigDecimal price;
    Integer quantity;
    Boolean isActive;
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(mappedBy = "produce")
    List<UserItem> userItems;

    @OneToMany(mappedBy = "produce")
    List<Review> reviews;

    @OneToMany(mappedBy = "produce")
    List<DiscountCode> discountCodes;
}
