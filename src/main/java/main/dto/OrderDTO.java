package main.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import main.enumerators.OrderStatus;
import main.enumerators.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
   String buyerName;
   String sellerName;
   private List<OrderItemDTO> orderItems;
   private String shippingAddress;
   private BigDecimal subtotal;
   private BigDecimal shippingCost;
}
