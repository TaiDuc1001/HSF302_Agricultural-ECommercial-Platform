package main.controller;

import main.dto.UserDTO;
import main.enumerators.Role;
import main.pojo.Order;
import main.pojo.OrderDetail;
import main.pojo.Produce;
import main.service.order.OrderService;
import main.service.order_item.OrderDetailService;
import main.service.produce.ProduceService;
import main.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    ProduceService produceService;

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        List<Order> orders = orderService.getOrdersBySellerId(user.getId());
        int totalProducts;
        Double totalRevenue;
        int totalOrders;
        int pendingOrders;
        if (orders != null && !orders.isEmpty()) {
            totalProducts = orders.stream().mapToInt(order -> order.getOrderDetails().size()).sum();
            totalRevenue = orders.stream()
                    .map(order -> order.getFinalAmount() != null ? order.getFinalAmount().doubleValue() : 0.0)
                    .reduce(0.0, Double::sum);
            totalOrders = orders.size();
            pendingOrders = (int) orders.stream().filter(order -> "CREATED".equals(order.getStatus().toString())).count();
        } else {
            totalProducts = 0;
            totalRevenue = 0.0;
            totalOrders = 0;
            pendingOrders = 0;
        }

        model.addAttribute("user", user);
        model.addAttribute("name", user.getName());
        
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("pendingOrders", pendingOrders);
        model.addAttribute("lastLogin", java.time.LocalDateTime.now().minusHours(2));
        
        return "seller/home";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Redirect to home for consistency
        return "redirect:/seller/home";
    }

    @GetMapping("/products")
    public String products(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        List<Produce> products = produceService.getProduceBySellerId(user.getId());
        model.addAttribute("products", products);
        return "seller/products";
    }

    @GetMapping("/orders")
    public String orders(HttpSession session, Model model,
                        @RequestParam(value = "status", required = false) String status) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        List<Order> orders = orderService.getOrdersBySellerId(user.getId());
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsBySellId(user.getId());
        int paidOrders;
        Double totalRevenue;
        int totalOrders;
        int createdOrders;
        if(orders != null && !orders.isEmpty()) {
            paidOrders = (int) orders.stream().filter(order -> "PAID".equals(order.getStatus().toString())).count();
            totalRevenue = orders.stream()
                    .map(order -> order.getFinalAmount() != null ? order.getFinalAmount().doubleValue() : 0.0)
                    .reduce(0.0, Double::sum);
            totalOrders = orders.size();
            createdOrders = (int) orders.stream().filter(order -> "CREATED".equals(order.getStatus().toString())).count();
        } else {
            paidOrders = 0;
            totalRevenue = 0.0;
            totalOrders = 0;
            createdOrders = 0;
        }

        model.addAttribute("user", user);
        model.addAttribute("name", user.getName());

        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("createdOrders", createdOrders);
        model.addAttribute("paidOrders", paidOrders);
        model.addAttribute("totalRevenue", totalRevenue);

        model.addAttribute("orders", orders);
        return "seller/orders";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        return "seller/profile";
    }

    @GetMapping("/add-product")
    public String addProduct(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        return "seller/add-product";
    }

    @GetMapping("/completed-orders/{id}")
    public String completedOrders(@PathVariable("id") Long id, HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        orderService.markOrderAsCompleted(id);
        return "redirect:/seller/orders";
    }

    @GetMapping("/cancelled-orders/{id}")
    public String cancelledOrders(@PathVariable("id") Long id, HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        orderService.cancelOrder(id);
        return "redirect:/seller/orders";
    }

    @GetMapping("/paid-orders/{id}")
    public String paidOrders(@PathVariable("id") Long id, HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"SELLER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        orderService.markOrderAsPaid(id);
        return "redirect:/seller/orders";
    }

    @GetMapping()
    public List<UserDTO> getAllSellers() {
        return userService.getUsersByRole(Role.valueOf("SELLER"));
    }
}
