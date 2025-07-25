package main.repository;

import main.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> getOrderDetailByProduce_User_Id(Long produceUserId);
}
