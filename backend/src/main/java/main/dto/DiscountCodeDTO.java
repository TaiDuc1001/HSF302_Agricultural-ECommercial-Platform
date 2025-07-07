package main.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountCodeDTO {
    Long id;
    String code;
    BigDecimal discountValue;
    LocalDate expirationDate;
    Boolean isActive;
}
