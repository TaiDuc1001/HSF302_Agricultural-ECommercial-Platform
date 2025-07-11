package main.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProduceDTO {
    Long id;
    String name;
    BigDecimal price;
    String imgUrl;
    Double averageRating;
    String description;
    Integer quantity;
    Boolean isActive;
    LocalDate preOrderStartDate;
    LocalDate preOrderEndDate;
}
