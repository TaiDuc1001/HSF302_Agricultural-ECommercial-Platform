package main.order;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import main.account.User;
import main.discount_code.DiscountCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;

    LocalDate orderedDate;
    OrderStatus Status;
    BigDecimal price;
    String shippingAddress;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    User buyer;

    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    DiscountCode discountCode;
}
