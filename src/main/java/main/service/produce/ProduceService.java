package main.service.produce;

import main.dto.ProduceDTO;
import main.dto.ProduceDetailDTO;
import main.pojo.Produce;

import java.util.List;
import java.util.Optional;

public interface ProduceService {
    List<ProduceDTO> getAllProduce();
    ProduceDetailDTO getProduceById(Long id);
}
