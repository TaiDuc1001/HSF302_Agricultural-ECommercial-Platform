package main.service.order_item;

import main.pojo.OrderDetail;
import main.pojo.UserItem;

import java.util.List;

public interface OrderDetailService {
    public abstract OrderDetail createOrderDetail(OrderDetail orderDetail);
    public abstract OrderDetail convertToOrderDetail(UserItem userItem, Long orderId);
    public abstract List<OrderDetail> getOrderDetailsBySellId(Long sellId);
}
