package main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import main.enumerators.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "order")
    List<UserItem> userItems;
}
