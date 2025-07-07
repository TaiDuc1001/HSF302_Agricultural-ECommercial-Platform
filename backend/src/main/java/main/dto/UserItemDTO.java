package main.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserItemDTO {
    Long id;
    Long userId;
    String userName;
    Long produceId;
    String produceName;
    BigDecimal producePrice;
    Integer quantity;
    BigDecimal totalPrice;
    Long orderId;
    Boolean isActive;
}
