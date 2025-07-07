package main.controller;

import lombok.RequiredArgsConstructor;
import main.entities.UserItem;
import main.service.user_item.UserItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-items")
@RequiredArgsConstructor
public class UserItemController {
    private final UserItemService userItemService;

    @GetMapping("/user/{userId}")
    public List<UserItem> getUserItemByUserId(@PathVariable Long userId) {
        return userItemService.findUserItemsByUserId(userId);
    }
}
