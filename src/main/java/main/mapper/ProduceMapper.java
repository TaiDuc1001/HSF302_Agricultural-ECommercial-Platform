package main.mapper;

import main.dto.ProduceDTO;
import main.pojo.Produce;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProduceMapper {
    ProduceDTO toDTO(Produce produce);
    Produce toEntity(ProduceDTO produceDTO);
    List<ProduceDTO> toDTOs(List<Produce> produces);
}
