package main.service.user_item;

import main.dto.UserItemDTO;
import main.entities.UserItem;

import java.util.List;

public interface UserItemService {
    public abstract List<UserItemDTO> findUserItemsByUserId(Long userId);
    public abstract List<UserItemDTO> toDTOs(List<UserItem> userItems);
}
