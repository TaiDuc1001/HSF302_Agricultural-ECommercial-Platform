package main.mapper;

import main.dto.OrderDTO;
import main.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "discountCode.code", target = "discountCode")
    OrderDTO toDTO(Order order);
    List<OrderDTO> toDTOs(List<Order> orders);
}
