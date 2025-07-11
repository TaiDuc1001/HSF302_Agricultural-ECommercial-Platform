package main.service.user_item;

import main.dto.UserItemDTO;
import main.pojo.UserItem;

import java.util.List;

public interface UserItemService {
    public abstract List<UserItem> findUserItemsByUserId(Long userId);
    public abstract List<UserItemDTO> toDTOs(List<UserItem> userItems);
    public abstract List<UserItem> findActiveUserItemsByUserId(Long userId);
    public abstract UserItemDTO toDTO(UserItem userItem);
    public abstract List<UserItem> disableUserItems(List<Long> userItemId);
    public abstract UserItemDTO disableUserItem(Long userItemId);
    public abstract UserItem updateUserItem (Long userItemId, UserItemDTO userItemDTO);
    public abstract UserItemDTO updateUserItemQuantity (Long userItemId, UserItemDTO userItemDTO);
    public abstract UserItemDTO addUserItem(UserItemDTO userItemDTO);
}
