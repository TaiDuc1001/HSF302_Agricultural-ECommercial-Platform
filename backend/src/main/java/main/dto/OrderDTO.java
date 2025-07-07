package main.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import main.enumerators.OrderStatus;
import main.enumerators.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {
   Long id;
   Long userId;
   LocalDate orderDate;
   BigDecimal totalAmount;
   BigDecimal discountAmount;
   BigDecimal finalAmount;
   boolean isPreOrder;
   LocalDate paymentDate;
   Boolean isActive;
   PaymentMethod paymentMethod;
   OrderStatus status;
   String discountCode;
}
