package main.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "produces")
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
    String imgUrl;

    @Transient
    Double averageRating;


    @Column(name = "preorder_startdate")
    LocalDate preOrderStartDate;

    @Column(name = "preorder_enddate")
    LocalDate preOrderEndDate;

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
