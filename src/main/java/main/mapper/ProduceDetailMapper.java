package main.mapper;

import main.dto.ProduceDetailDTO;
import main.pojo.Produce;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduceDetailMapper {
    @Mapping(target = "userId", source = "produce.user.id")
    @Mapping(target = "userName", source = "produce.user.name")
    @Mapping(target = "categoryId", source = "produce.category.id")
    @Mapping(target = "categoryName", source = "produce.category.name")
    ProduceDetailDTO toDTO(Produce produce);
    Produce toEntity(ProduceDetailDTO produceDetailDTO);

}
