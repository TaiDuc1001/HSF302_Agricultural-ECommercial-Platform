package main.service.user_item;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.dto.UserItemDTO;
import main.pojo.Produce;
import main.pojo.User;
import main.pojo.UserItem;
import main.mapper.UserItemMapper;
import main.repository.ProduceRepository;
import main.repository.UserItemRepository;
import main.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserItemServiceImpl implements UserItemService {
    private final UserItemRepository userItemRepository;
    private final UserItemMapper userItemMapper;
    private final UserRepository userRepository;
    private final ProduceRepository produceRepository;


    @Override
    public List<UserItemDTO> findUserItemsByUserId(Long userId) {
        return toDTOs(userItemRepository.findUserItemsByUserId(userId));
    }

    @Override
    public List<UserItemDTO> findActiveUserItemsByUserId(Long userId) {
        List<UserItem> userItems = userItemRepository.findUserItemsByUserId(userId).stream()
                .filter(UserItem::getIsActive)
                .toList();
        return toDTOs(userItems);
    }

    @Override
    public UserItemDTO createUserItem(UserItemDTO userItemDTO) {
        User user = userRepository.findById(userItemDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userItemDTO.getUserId()));

        Produce produce = produceRepository.findById(userItemDTO.getProduceId()).orElseThrow(() -> new EntityNotFoundException("Produce not found with id: " + userItemDTO.getProduceId()));

        UserItem userItem = UserItem.builder()
                .id(userItemDTO.getUserId())
                .user(user)
                .produce(produce)
                .quantity(userItemDTO.getQuantity())
                .isActive(true)
                .build();
        return toDTO(userItemRepository.save(userItem));
    }


    @Override
    public List<UserItemDTO> disableUserItems(List<Long> userItemId) {
        return userItemId.stream()
                .map(this::disableUserItem)
                .toList();
    }

    @Override
    public UserItemDTO toDTO(UserItem userItem) {
        return userItemMapper.toDTO(userItem);
    }

    @Override
    public List<UserItemDTO> toDTOs(List<UserItem> userItems) {
        return userItemMapper.toDTOs(userItems);
    }

    private UserItem findUserItemById(Long userItemId) {
        return userItemRepository.findById(userItemId)
                .orElseThrow(() -> new EntityNotFoundException("UserItem not found with id: " + userItemId));
    }

    private UserItemDTO disableUserItem(Long userItemId) {
        UserItem userItem = findUserItemById(userItemId);
        userItem.setIsActive(false);
        return toDTO(userItemRepository.save(userItem));
    }

    @Override
    public UserItemDTO updateUserItem(Long userItemId, UserItemDTO userItemDTO) {
        User user = userRepository.findById(userItemDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userItemDTO.getUserId()));

        Produce produce = produceRepository.findById(userItemDTO.getProduceId()).orElseThrow(() -> new EntityNotFoundException("Produce not found with id: " + userItemDTO.getProduceId()));

        UserItem userItem = findUserItemById(userItemId).toBuilder()
                .user(user)
                .produce(produce)
                .quantity(userItemDTO.getQuantity())
                .isActive(userItemDTO.getIsActive())
                .build();

        return toDTO(userItemRepository.save(userItem));
    }

    @Override
    public UserItemDTO updateUserItemQuantity(Long userItemId, UserItemDTO userItemDTO) {
        UserItem userItem = findUserItemById(userItemId).toBuilder()
                .quantity(userItemDTO.getQuantity())
                .build();
        return toDTO(userItemRepository.save(userItem));
    }
}
