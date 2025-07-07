package main.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import main.enumerators.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {
   Long id;
   Long userId;
   String shippingAddress;
   LocalDate orderedDate;
   OrderStatus status;
   BigDecimal price;
   String discountCode;
}
