package main.user_item;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import main.account.User;
import main.order.Order;
import main.produce.Produce;

@Data
@Entity(name = "user_item")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "produce_id")
    Produce produce;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @Column(nullable = false)
    Integer quantity;

    @Column(name = "is_active", nullable = false)
    Boolean isActive = true;
}

