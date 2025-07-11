package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.OrderDTO;
import main.dto.ProduceDTO;
import main.dto.UserDTO;
import main.enumerators.OrderStatus;
import main.pojo.*;
import main.service.order.OrderService;
import main.service.order_item.OrderDetailService;
import main.service.produce.ProduceService;
import main.service.user.UserService;
import main.service.user_item.UserItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/buyer")
@RequiredArgsConstructor
public class BuyerController {

    private final OrderService orderService;
    private final ProduceService produceService;
    private final UserItemService userItemService;
    private final OrderDetailService orderDetailService;


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

    @PostMapping("/checkout/process")
    public String processCheckout(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"BUYER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        List<UserItem> userItems = userItemService.findActiveUserItemsByUserId(user.getId());
        if (userItems.isEmpty()) {
            model.addAttribute("error", "Your cart is empty.");
            return "buyer/cart";
        }

        BigDecimal totalPrice = userItems.stream()
                .map(item -> item.getProduce().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        boolean isPreorder = userItems.stream()
                .anyMatch(item -> item.getProduce().getPreOrderStartDate().isAfter(LocalDate.now()) &&
                                  item.getProduce().getPreOrderEndDate().isBefore(LocalDate.now()) );

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(user.getId());
        orderDTO.setTotalAmount(totalPrice);
        orderDTO.setPreOrder(isPreorder);
        orderDTO.setDiscountCode("PORN30");

        OrderDTO savedOrder = orderService.createOrder(orderDTO);

        for (UserItem item : userItems) {
            orderDetailService.convertToOrderDetail(item,savedOrder.getId());
        }

        userItemService.disableUserItems(user.getId());

        model.addAttribute("order", savedOrder);
        return "redirect:/buyer/home";
    }
}