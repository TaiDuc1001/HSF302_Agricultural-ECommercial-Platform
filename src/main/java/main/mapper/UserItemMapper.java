package main.mapper;

import main.dto.UserItemDTO;
import main.entities.UserItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserItemMapper {
    UserItem toEntity(UserItemDTO userItemDTO);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "produce.id", target = "produceId")
    @Mapping(source = "produce.name", target = "produceName")
    @Mapping(source = "produce.price", target = "producePrice")
    @Mapping(target = "totalPrice", expression = "java(userItem.getProduce().getPrice().multiply(new BigDecimal(userItem.getQuantity())))")
    UserItemDTO toDTO(UserItem userItem);

    List<UserItemDTO> toDTOs(List<UserItem> userItems);
}
