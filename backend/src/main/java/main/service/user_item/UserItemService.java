package main.service.user_item;

import main.entities.UserItem;

import java.util.List;

public interface UserItemService {
    public abstract List<UserItem> findUserItemsByUserId(Long userId);
}
