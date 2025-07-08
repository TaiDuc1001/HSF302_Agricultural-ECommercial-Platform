package main.controller.api;

import lombok.RequiredArgsConstructor;
import main.dto.UserItemDTO;
import main.service.user_item.UserItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-items")
@RequiredArgsConstructor
public class UserItemController {
    private final UserItemService userItemService;

    @GetMapping("/user/{userId}")
    public List<UserItemDTO> getActiveUserItemByUserId(@PathVariable Long userId) {
        return userItemService.findActiveUserItemsByUserId(userId);
    }

    @PostMapping
    public UserItemDTO createUserItem(@RequestBody UserItemDTO userItemDTO) {
        return userItemService.createUserItem(userItemDTO);
    }

    @PutMapping("/{id}")
    public UserItemDTO updateUserItem(@PathVariable Long id, @RequestBody UserItemDTO userItemDTO) {
        return userItemService.updateUserItem(id, userItemDTO);
    }

    @PatchMapping("/{id}")
    public UserItemDTO updateUserItemQuantity(@PathVariable Long id, @RequestBody UserItemDTO userItemDTO) {
        return userItemService.updateUserItemQuantity(id, userItemDTO);
    }

    @DeleteMapping("/user/{userId}")
    public List<UserItemDTO> disableUserItems(@PathVariable Long userId) {
        List<Long> userItemIds = userItemService.findActiveUserItemsByUserId(userId).stream()
                .map(UserItemDTO::getId)
                .toList();
        return userItemService.disableUserItems(userItemIds);
    }
}
