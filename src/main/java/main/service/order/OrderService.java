package main.service.order;

import main.dto.OrderDTO;
import main.pojo.Order;

import java.util.List;

public interface OrderService {
    public abstract OrderDTO createOrder(OrderDTO orderDTO);
    public abstract Order getOrderById(Long id);
    public abstract List<OrderDTO> getAllOrders();
    public abstract List<Order> getOrdersByUserId(Long userId);
    public abstract OrderDTO updateOrder(Long id, OrderDTO orderDTO);
    public abstract OrderDTO cancelOrder(Long id);
    public abstract OrderDTO toDTO(Order order);
    public abstract List<OrderDTO> toDTOs(List<Order> orders);
    public abstract OrderDTO updateOrderStatus(Long id, OrderDTO orderDTO);
    public abstract List<Order> getOrdersBySellerId(Long sellerId);
    public abstract void markOrderAsCompleted(Long orderId);
    public abstract void markOrderAsCancelled(Long orderId);
    public abstract void markOrderAsPaid(Long orderId);

}
