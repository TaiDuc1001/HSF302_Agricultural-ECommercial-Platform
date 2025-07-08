package main.service.user_item;

import main.dto.UserItemDTO;
import main.pojo.UserItem;

import java.util.List;

public interface UserItemService {
    public abstract List<UserItemDTO> findUserItemsByUserId(Long userId);
    public abstract List<UserItemDTO> toDTOs(List<UserItem> userItems);
    public abstract List<UserItemDTO> findActiveUserItemsByUserId(Long userId);
    public abstract UserItemDTO createUserItem(UserItemDTO userItemDTO);
    public abstract UserItemDTO toDTO(UserItem userItem);
    public abstract List<UserItemDTO> disableUserItems(List<Long> userItemId);
    public abstract UserItemDTO updateUserItem (Long userItemId, UserItemDTO userItemDTO);
    public abstract UserItemDTO updateUserItemQuantity (Long userItemId, UserItemDTO userItemDTO);
}
