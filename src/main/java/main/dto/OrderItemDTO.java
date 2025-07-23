package main.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
