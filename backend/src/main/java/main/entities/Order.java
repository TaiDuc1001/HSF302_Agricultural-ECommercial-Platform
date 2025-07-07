package main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import main.enumerators.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table (name = "orders")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;

    LocalDate orderedDate;

    @Enumerated(EnumType.STRING)
    OrderStatus status;
    BigDecimal price;
    String shippingAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    DiscountCode discountCode;
}
