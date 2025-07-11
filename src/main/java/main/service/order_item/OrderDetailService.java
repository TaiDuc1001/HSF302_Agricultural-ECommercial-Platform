package main.service.order_item;

import main.pojo.OrderDetail;
import main.pojo.UserItem;

public interface OrderDetailService {
    public abstract OrderDetail createOrderDetail(OrderDetail orderDetail);
    public abstract OrderDetail convertToOrderDetail(UserItem userItem, Long orderId);
}
