package main.service.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.dto.OrderDTO;
import main.pojo.DiscountCode;
import main.pojo.Order;
import main.pojo.User;
import main.enumerators.DiscountType;
import main.enumerators.OrderStatus;
import main.mapper.OrderMapper;
import main.repository.DiscountCodeRepository;
import main.repository.OrderRepository;
import main.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final DiscountCodeRepository discountCodeRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + orderDTO.getUserId()));

        Order order = Order.builder()
                .id(null)
                .user(user)
                .orderDate(LocalDate.now())
                .totalAmount(orderDTO.getTotalAmount())
                .discountAmount(BigDecimal.valueOf(0))
                .finalAmount(orderDTO.getTotalAmount())
                .isPreOrder(orderDTO.isPreOrder())
                .isActive(true)
                .status(OrderStatus.CREATED)
                .build();

        if(orderDTO.isPreOrder()){
            DiscountCode discountCode = discountCodeRepository.findDiscountCodeByCode(orderDTO.getDiscountCode());
            BigDecimal discountAmount = BigDecimal.valueOf(0);
            if(discountCode.getType() == DiscountType.FIXED){
                discountAmount = discountCode.getValue();
            }else if(discountCode.getType() == DiscountType.PERCENTAGE){
                discountAmount = orderDTO.getTotalAmount().multiply(discountCode.getValue().divide(BigDecimal.valueOf(100)));
            }

            order = order.toBuilder()
                    .discountCode(discountCode)
                    .discountAmount(discountAmount)
                    .finalAmount(orderDTO.getTotalAmount().subtract(discountAmount))
                    .build();
        }

        return toDTO(orderRepository.save(order));
    }

    @Override
    public Order getOrderById(Long id) {
        return (orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id)));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return toDTOs(orderRepository.findAll());
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + orderDTO.getUserId()));

        DiscountCode discountCode = discountCodeRepository.findDiscountCodeByCode(orderDTO.getDiscountCode());

        Order order = findOrderById(id).toBuilder()
                .user(user)
                .discountCode(discountCode)
                .orderDate(orderDTO.getOrderDate())
                .totalAmount(orderDTO.getTotalAmount())
                .discountAmount(orderDTO.getDiscountAmount())
                .finalAmount(orderDTO.getTotalAmount())
                .isPreOrder(orderDTO.isPreOrder())
                .isActive(orderDTO.getIsActive())
                .paymentDate(orderDTO.getPaymentDate())
                .paymentMethod(orderDTO.getPaymentMethod())
                .status(orderDTO.getStatus())
                .build();

        return toDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO cancelOrder(Long id) {
        Order order = findOrderById(id);
        order.setStatus(OrderStatus.CANCELLED);
        return toDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO toDTO(Order order) {
        return orderMapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> toDTOs(List<Order> orders) {
        return orderMapper.toDTOs(orders);
    }

    @Override
    public OrderDTO updateOrderStatus(Long id, OrderDTO orderDTO) {
        Order order = findOrderById(id);
        if(orderDTO.getStatus() == null) {
            throw new IllegalArgumentException("Order status cannot be null");
        }
        if(orderDTO.getStatus().ordinal() < order.getStatus().ordinal()) {
            throw new IllegalStateException("Cannot update order status to a previous state");
        }
        if(orderDTO.getStatus().ordinal() > order.getStatus().ordinal() + 1) {
            throw new IllegalStateException("Cannot skip order status updates");
        }
        if(orderDTO.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cannot update order status to CANCELLED directly");
        }
        order.setStatus(orderDTO.getStatus());
        return toDTO(orderRepository.save(order));
    }

    private Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }
}
