package main.service.order;

import main.dto.OrderDTO;
import main.entities.Order;

import java.util.List;

public interface OrderService {
    public abstract OrderDTO createOrder(OrderDTO orderDTO);
    public abstract OrderDTO getOrderById(Long id);
    public abstract List<OrderDTO> getAllOrders();
    public abstract List<OrderDTO> getOrdersByUserId(Long userId);
    public abstract OrderDTO updateOrder(Long id, OrderDTO orderDTO);
    public abstract OrderDTO cancelOrder(Long id);
    public abstract OrderDTO toDTO(Order order);
    public abstract List<OrderDTO> toDTOs(List<Order> orders);
    public abstract OrderDTO updateOrderStatus(Long id, OrderDTO orderDTO);
}
