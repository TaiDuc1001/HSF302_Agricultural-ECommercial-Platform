package main.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import main.enumerators.OrderStatus;
import main.enumerators.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    LocalDate orderDate;
    BigDecimal totalAmount;
    BigDecimal discountAmount;
    BigDecimal finalAmount;
    LocalDate paymentDate;
    Boolean isActive;

    @Column(name = "is_preorder")
    boolean isPreOrder;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "discount_code")
    DiscountCode discountCode;

    @OneToMany(mappedBy = "order")
    List<Review> reviews;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}
