package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.UserItemDTO;
import main.pojo.UserItem;
import main.service.user_item.UserItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-items")
@RequiredArgsConstructor
public class UserItemController {
    private final UserItemService userItemService;

    @GetMapping("/user/{userId}")
    public List<UserItem> getActiveUserItemByUserId(@PathVariable Long userId) {
        return userItemService.findActiveUserItemsByUserId(userId);
    }

    @PostMapping
    public UserItemDTO createUserItem(@RequestBody UserItemDTO userItemDTO) {
        return userItemService.addUserItem(userItemDTO);
    }

    @PutMapping("/{id}")
    public UserItem updateUserItem(@PathVariable Long id, @RequestBody UserItemDTO userItemDTO) {
        return userItemService.updateUserItem(id, userItemDTO);
    }

    @PatchMapping("/{id}")
    public UserItemDTO updateUserItemQuantity(@PathVariable Long id, @RequestBody UserItemDTO userItemDTO) {
        return userItemService.updateUserItemQuantity(id, userItemDTO);
    }

    @DeleteMapping("/user/{userId}")
    public List<UserItemDTO> disableUserItems(@PathVariable Long userId) {
        return userItemService.disableUserItems(userId);
    }

    @DeleteMapping("/{id}")
    public UserItemDTO disableUserItem(@PathVariable Long id) {
        return userItemService.disableUserItem(id);
    }
}
