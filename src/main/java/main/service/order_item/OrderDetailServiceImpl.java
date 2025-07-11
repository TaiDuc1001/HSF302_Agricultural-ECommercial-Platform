package main.service.order_item;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.pojo.Order;
import main.pojo.OrderDetail;
import main.pojo.UserItem;
import main.repository.OrderDetailRepository;
import main.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail convertToOrderDetail(UserItem userItem, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        OrderDetail orderDetail = new OrderDetail(null, userItem.getQuantity(), userItem.getProduce().getPrice(), order, userItem.getProduce());
        return createOrderDetail(orderDetail);
    }
}
