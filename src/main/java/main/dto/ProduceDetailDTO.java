package main.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProduceDetailDTO {
    Long id;
    String name;
    String description;
    BigDecimal price;
    Integer quantity;
    Boolean isActive;
    String imgUrl;
    String preOrderStartDate;
    String preOrderEndDate;
    Long userId;
    String userName;
    Long categoryId;
    String categoryName;
}
