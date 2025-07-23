package main.repository;

import main.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    List<Order> findByOrderDetails_Produce_User_Id(Long orderDetailsProduceUserId);

    int countByUser_Id(Long userId);

    List<Order> findAllByOrderDetails_Produce_User_Id(Long orderDetailsProduceUserId);
}
