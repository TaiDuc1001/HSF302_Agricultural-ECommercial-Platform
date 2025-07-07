package main.produce;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import main.account.User;
import main.category.Category;

import java.math.BigDecimal;

@Entity
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
    Integer stockQuantity;
    Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    User seller;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
