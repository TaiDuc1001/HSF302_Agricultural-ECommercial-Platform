package main.controller;

import main.dto.OrderDTO;
import main.dto.ProduceDTO;
import main.dto.UserDTO;
import main.pojo.*;
import main.service.order.OrderService;
import main.service.produce.ProduceService;
import main.service.user_item.UserItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

    private final OrderService orderService;
    private final ProduceService produceService;
    private final UserItemService userItemService;

    public BuyerController(OrderService orderService, ProduceService produceService, UserItemService userItemService) {
        this.orderService = orderService;
        this.produceService = produceService;
        this.userItemService = userItemService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        return "redirect:/buyer/home";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }


        model.addAttribute("user", user);
        model.addAttribute("name", user.getName());
        model.addAttribute("totalOrders", 3);
        model.addAttribute("cartItems", 2);
        model.addAttribute("pendingReviews", 1);
        model.addAttribute("produces", produceService.getProduceWithAverageRating());
        
        return "buyer/home";
    }

    @GetMapping("/orders")
    public String orders(HttpSession session, Model model, 
                        @RequestParam(value = "status", required = false) String status) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        model.addAttribute("orders", orders);
        model.addAttribute("address", user.getAddress());
        return "buyer/orders";
    }

    @GetMapping("/wishlist")
    public String wishlist(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "buyer/wishlist";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        return "buyer/profile";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        List<UserItem> userItems = userItemService.findActiveUserItemsByUserId(user.getId());
        BigDecimal totalPrice = userItems.stream()
                .map(item -> item.getProduce().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("cartItems", userItems);
        model.addAttribute("total", totalPrice);
        return "buyer/cart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        List<UserItem> userItems = userItemService.findActiveUserItemsByUserId(user.getId());
        BigDecimal totalPrice = userItems.stream()
                .map(item -> item.getProduce().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("user", user);
        model.addAttribute("cartItems", userItems);
        model.addAttribute("total", totalPrice);
        return "buyer/checkout";
    }

    @GetMapping("/order-detail/{id}")
    public String orderDetail(@PathVariable Long id,HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return "redirect:/buyer/orders";
        }
        model.addAttribute("order", order);
        return "buyer/order-detail";
    }

//    @GetMapping("/order-detail/{id}")
//    public String orderDetail(@PathVariable Long id, Model model) {
//        // Create fake order details
//        List<FakeOrderDetail> orderDetails = Arrays.asList(
//                new FakeOrderDetail("Fresh Tomatoes", "Green Valley Farm", 3, 4.99, "/images/tomatoes.jpg"),
//                new FakeOrderDetail("Organic Carrots", "Sunshine Gardens", 2, 6.50, "/images/carrots.jpg"),
//                new FakeOrderDetail("Bell Peppers", "Mountain View Farm", 4, 3.25, "/images/peppers.jpg")
//        );
//
//        // Create fake order
//        FakeOrder order = new FakeOrder(
//                id,
//                LocalDateTime.now().minusDays(3),
//                "delivered",
//                43.47,
//                5.00,
//                "SAVE10",
//                38.47,
//                "Credit Card",
//                orderDetails
//        );
//
//        model.addAttribute("order", order);
//        return "buyer/order-detail";
//    }
}
