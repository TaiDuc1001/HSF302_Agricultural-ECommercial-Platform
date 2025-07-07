package main.service.user_item;

import lombok.RequiredArgsConstructor;
import main.entities.UserItem;
import main.repository.UserItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserItemServiceImpl implements UserItemService {
    private final UserItemRepository userItemRepository;

    @Override
    public List<UserItem> findUserItemsByUserId(Long userId) {
        return userItemRepository.findUserItemsByUserId(userId);
    }
}
