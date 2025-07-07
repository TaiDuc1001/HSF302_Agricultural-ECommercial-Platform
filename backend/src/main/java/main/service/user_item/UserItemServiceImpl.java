package main.service.user_item;

import lombok.RequiredArgsConstructor;
import main.dto.UserItemDTO;
import main.entities.UserItem;
import main.mapper.UserItemMapper;
import main.repository.UserItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserItemServiceImpl implements UserItemService {
    private final UserItemRepository userItemRepository;
    private final UserItemMapper userItemMapper;

    @Override
    public List<UserItemDTO> findUserItemsByUserId(Long userId) {
        return toDTOs(userItemRepository.findUserItemsByUserId(userId));
    }

    @Override
    public List<UserItemDTO> toDTOs(List<UserItem> userItems) {
        return userItemMapper.toDTOs(userItems);
    }
}
