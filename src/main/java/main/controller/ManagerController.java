package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.CategoryDTO;
import main.dto.OrderDTO;
import main.dto.OrderItemDTO;
import main.dto.UserDTO;
import main.enumerators.Role;
import main.pojo.Order;
import main.pojo.OrderDetail;
import main.pojo.Produce;
import main.service.category.CategoryService;
import main.service.order.OrderService;
import main.service.produce.ProduceService;
import main.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final UserService userService;
    private final ProduceService produceService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("name", user.getName());
        model.addAttribute("totalUsers", 150);
        model.addAttribute("totalProducts", 75);
        model.addAttribute("totalOrders", 320);
        model.addAttribute("activeSellers", 25);

        return "manager/home";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        return "redirect:/manager/home";
    }

    @GetMapping("/users")
    public String users(HttpSession session, Model model,
                        @RequestParam(required = false) String role,
                        @RequestParam(required = false) String search) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        List<UserDTO> users;

        // Nếu có search term, search trước
        if (search != null && !search.trim().isEmpty()) {
            users = userService.searchUsers(search);
        }
        // Nếu có role filter và không phải empty (tức là không phải "All Roles")
        else if (role != null && !role.trim().isEmpty()) {
            try {
                users = userService.getUsersByRole(Role.valueOf(role.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Nếu role không hợp lệ, lấy tất cả users
                users = userService.getAllUsers();
            }
        }
        // Mặc định lấy tất cả users (khi role = "" hoặc null)
        else {
            users = userService.getAllUsers();
        }

        // Calculate statistics
        long totalUsers = userService.getAllUsers().size();
        long totalBuyers = userService.getUsersByRole(Role.BUYER).size();
        long totalSellers = userService.getUsersByRole(Role.SELLER).size();
        long totalManagers = userService.getUsersByRole(Role.MANAGER).size();

        model.addAttribute("name", user.getName());
        model.addAttribute("users", users);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalBuyers", totalBuyers);
        model.addAttribute("totalSellers", totalSellers);
        model.addAttribute("totalManagers", totalManagers);

        // Thêm current filters để giữ lại state
        model.addAttribute("currentRole", role);
        model.addAttribute("currentSearch", search);
        return "manager/users";
    }

    @PostMapping("/users/{userId}/toggle-status")
    @ResponseBody
    public Map<String, Object> toggleUserStatus(@PathVariable Long userId,
                                                @RequestBody Map<String, Boolean> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Boolean activate = request.get("activate");
            UserDTO updatedUser = userService.updateUserStatus(userId, activate);
            response.put("success", true);
            response.put("message", "User status updated successfully");
            response.put("user", updatedUser);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update user status: " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/users/{userId}")
    public String viewUser(@PathVariable Long userId, HttpSession session, Model model) {
        UserDTO currentUser = (UserDTO) session.getAttribute("user");
        if (currentUser == null || !"MANAGER".equals(currentUser.getRole().toString())) {
            return "redirect:/auth/login";
        }

        try {
            UserDTO user = userService.getUserById(userId);
            model.addAttribute("name", currentUser.getName());
            model.addAttribute("user", user);
            return "manager/user-detail";
        } catch (Exception e) {
            return "redirect:/manager/users";
        }
    }

    @GetMapping("/products")
    public String products(HttpSession session, Model model,
                           @RequestParam(required = false) String status,
                           @RequestParam(required = false) Long category,
                           @RequestParam(required = false) String search) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        List<Produce> allProducts = produceService.getProduceWithAverageRating();
        List<CategoryDTO> allCategories = categoryService.getAllCategories();
        List<Produce> filteredProducts = allProducts.stream()
                .filter(p -> {
                    if (status == null || status.isEmpty()) {
                        return true;
                    }
                    boolean statusBool = "ACTIVE".equalsIgnoreCase(status);
                    return p.getIsActive() == statusBool;
                })
                .filter(p -> (category == null) || p.getCategory().getId().equals(category))
                .filter(p -> (search == null || search.isEmpty()) || p.getName().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
        long totalProducts = allProducts.size();
        long activeProducts = allProducts.stream().filter(p -> p.getIsActive() == true).count();
        long inactiveProducts = totalProducts - activeProducts;
        model.addAttribute("name", user.getName());
        model.addAttribute("products", filteredProducts);
        model.addAttribute("categories", allCategories);
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("activeProducts", activeProducts);
        model.addAttribute("inactiveProducts", inactiveProducts);
        model.addAttribute("currentStatus", status);
        model.addAttribute("currentCategory", category);
        model.addAttribute("currentSearch", search);
        return "manager/products";
    }

    @GetMapping("/products/{productId}")
    public String productDetail(@PathVariable Long productId, HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        try {
            Produce product = produceService.getProduceById(productId);
            model.addAttribute("name", user.getName());
            model.addAttribute("product", product);
            return "manager/product-detail";
        } catch (Exception e) {
            return "redirect:/manager/products";
        }
    }

    @PostMapping("/products/{productId}/status")
    @ResponseBody
    public Map<String, Object> updateProductStatus(@PathVariable Long productId, @RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String status = request.get("status");
            boolean isActive = "ACTIVE".equalsIgnoreCase(status);
            produceService.updateProduceStatus(productId, isActive);
            response.put("success", true);
            response.put("message", "Product status updated successfully.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update product status: " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/orders")
    public String orders(
            HttpSession session,
            Model model,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo,
            @RequestParam(required = false) String search) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }

        List<OrderDTO> allOrders = orderService.getAllOrders();

        // Map userId -> userName, userId -> address
        Map<Long, UserDTO> userIdToUser = userService.getAllUsers().stream()
                .collect(Collectors.toMap(UserDTO::getId, u -> u));

        // Set buyerName, shippingAddress, sellerName, orderItems cho từng order
        for (OrderDTO order : allOrders) {
            UserDTO buyer = userIdToUser.get(order.getUserId());
            order.setBuyerName(buyer != null ? buyer.getName() : "");
            order.setShippingAddress(buyer != null ? buyer.getAddress() : "");

            // Lấy sellerName từ OrderDetail đầu tiên (nếu có)
            String sellerName = "";
            List<OrderDetail> details = new ArrayList<>();
            Order orderEntity = orderService.getOrderById(order.getId());
            if (orderEntity != null && orderEntity.getOrderDetails() != null && !orderEntity.getOrderDetails().isEmpty()) {
                details = orderEntity.getOrderDetails();
                var firstDetail = details.get(0);
                if (firstDetail.getProduce() != null && firstDetail.getProduce().getUser() != null) {
                    sellerName = firstDetail.getProduce().getUser().getName();
                }
            }
            order.setSellerName(sellerName);

            // Map orderItems
            List<OrderItemDTO> items = new ArrayList<>();
            for (OrderDetail od : details) {
                OrderItemDTO item = new OrderItemDTO();
                item.setProductName(od.getProduce().getName());
                item.setQuantity(od.getQuantity());
                item.setPrice(od.getUnitPrice());
                items.add(item);
            }
            order.setOrderItems(items);
        }

        // Filter theo trạng thái
        if (status != null && !status.isEmpty()) {
            allOrders = allOrders.stream()
                    .filter(o -> o.getStatus() != null && o.getStatus().toString().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }

        // Filter theo ngày
        if (dateFrom != null && !dateFrom.isEmpty()) {
            LocalDate from = LocalDate.parse(dateFrom);
            allOrders = allOrders.stream()
                    .filter(o -> o.getOrderDate() != null && !o.getOrderDate().isBefore(from))
                    .collect(Collectors.toList());
        }
        if (dateTo != null && !dateTo.isEmpty()) {
            LocalDate to = LocalDate.parse(dateTo);
            allOrders = allOrders.stream()
                    .filter(o -> o.getOrderDate() != null && !o.getOrderDate().isAfter(to))
                    .collect(Collectors.toList());
        }

        // Search theo buyer/seller name hoặc id
// Search theo tên sản phẩm (orderName), buyer, seller, id
        if (search != null && !search.trim().isEmpty()) {
            String searchLower = search.trim().toLowerCase();
            allOrders = allOrders.stream()
                    .filter(o -> {
                        // Search theo tên sản phẩm trong orderItems
                        boolean matchProduct = false;
                        if (o.getOrderItems() != null) {
                            matchProduct = o.getOrderItems().stream()
                                    .anyMatch(item -> item.getProductName() != null && item.getProductName().toLowerCase().contains(searchLower));
                        }
                        String buyerName = o.getBuyerName();
                        String sellerName = o.getSellerName();
                        return matchProduct
                                || (buyerName != null && buyerName.toLowerCase().contains(searchLower))
                                || (sellerName != null && sellerName.toLowerCase().contains(searchLower))
                                || (o.getId() != null && o.getId().toString().contains(searchLower));
                    })
                    .collect(Collectors.toList());
        }

        // Thống kê
        long totalOrders = allOrders.size();
        long pendingOrders = allOrders.stream().filter(o -> o.getStatus() != null && o.getStatus().toString().equals("CREATED")).count();
        long completedOrders = allOrders.stream().filter(o -> o.getStatus() != null && o.getStatus().toString().equals("COMPLETED")).count();
        long cancelledOrders = allOrders.stream().filter(o -> o.getStatus() != null && o.getStatus().toString().equals("CANCELLED")).count();
        double totalRevenue = allOrders.stream()
                .filter(o -> o.getFinalAmount() != null)
                .mapToDouble(o -> o.getFinalAmount().doubleValue())
                .sum();

        model.addAttribute("orders", allOrders);
        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("pendingOrders", pendingOrders);
        model.addAttribute("completedOrders", completedOrders);
        model.addAttribute("cancelledOrders", cancelledOrders);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("name", user.getName());
        model.addAttribute("currentStatus", status);
        model.addAttribute("currentSearch", search);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);

        return "manager/orders";
    }

    @GetMapping("/sellers")
    public String sellers(
            HttpSession session,
            Model model,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"MANAGER".equals(user.getRole().toString())) {
            return "redirect:/auth/login";
        }
        List<UserDTO> sellers = userService.getUsersByRole(Role.SELLER);

        // Filter theo trạng thái
        if (status != null && !status.isEmpty()) {
            boolean isActive = "ACTIVE".equalsIgnoreCase(status);
            sellers = sellers.stream()
                    .filter(s -> Boolean.TRUE.equals(s.getIsActive()) == isActive)
                    .collect(Collectors.toList());
        }

        // Search theo tên hoặc email
        if (search != null && !search.trim().isEmpty()) {
            String searchLower = search.trim().toLowerCase();
            sellers = sellers.stream()
                    .filter(s -> (s.getName() != null && s.getName().toLowerCase().contains(searchLower)) ||
                            (s.getEmail() != null && s.getEmail().toLowerCase().contains(searchLower)))
                    .collect(Collectors.toList());
        }

        // Bổ sung thông tin thống kê cho từng seller
        for (UserDTO seller : sellers) {
            Long sellerId = seller.getId();
            seller.setProductCount(userService.countProductsBySellerId(sellerId));
            seller.setOrderCount(userService.countOrdersBySellerId(sellerId));
            seller.setTotalRevenue(userService.sumRevenueBySellerId(sellerId));
            seller.setRating(userService.averageRatingBySellerId(sellerId));
            seller.setReviewCount(userService.countReviewsBySellerId(sellerId));
        }

        model.addAttribute("sellers", sellers);
        model.addAttribute("totalSellers", sellers.size());
        long activeSellers = sellers.stream().filter(UserDTO::getIsActive).count();
        model.addAttribute("activeSellers", activeSellers);
        model.addAttribute("inactiveSellers", sellers.size() - activeSellers);
        model.addAttribute("name", user.getName());
        model.addAttribute("currentStatus", status);
        model.addAttribute("currentSearch", search);
        return "manager/sellers";
    }
    @GetMapping("/sellers/{sellerId}")
    public String viewSellerDetail(@PathVariable Long sellerId, HttpSession session, Model model) {
        UserDTO manager = (UserDTO) session.getAttribute("user");
        if (manager == null || !"MANAGER".equals(manager.getRole().toString())) {
            return "redirect:/auth/login";
        }
        UserDTO seller = userService.getUserById(sellerId);
        // Bổ sung các thống kê nếu cần
        seller.setProductCount(userService.countProductsBySellerId(sellerId));
        seller.setOrderCount(userService.countOrdersBySellerId(sellerId));
        seller.setTotalRevenue(userService.sumRevenueBySellerId(sellerId));
        seller.setRating(userService.averageRatingBySellerId(sellerId));
        seller.setReviewCount(userService.countReviewsBySellerId(sellerId));
        model.addAttribute("seller", seller);
        model.addAttribute("name", manager.getName());
        return "manager/seller-detail";
    }
    @PostMapping("/sellers/{sellerId}/status")
    @ResponseBody
    public Map<String, Object> changeSellerStatus(@PathVariable Long sellerId, @RequestBody Map<String, Boolean> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Boolean activate = request.get("activate");
            UserDTO updatedSeller = userService.updateUserStatus(sellerId, activate);
            response.put("success", true);
            response.put("seller", updatedSeller);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update seller status: " + e.getMessage());
        }
        return response;
    }
}
