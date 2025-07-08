package main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import main.enumerators.DiscountType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount_codes")
public class DiscountCode {
    @Id
    @Column(unique = true)
    String code;

    @Enumerated(EnumType.STRING)
    DiscountType type;

    int maxUses;
    int usedCount;
    BigDecimal value;
    LocalDate validFrom;
    LocalDate validUntil;
    Boolean isActive;

    @OneToMany(mappedBy = "discountCode")
    List<Order> orderList;

    @ManyToOne
    @JoinColumn(name = "produce_id")
    Produce produce;
}
