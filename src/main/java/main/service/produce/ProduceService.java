package main.service.produce;

import main.dto.ProduceDTO;
import main.dto.ProduceDetailDTO;
import main.pojo.Produce;

import java.util.List;
import java.util.Optional;

public interface ProduceService {
    List<Produce> getAllProduce();
    Produce getProduceById(Long id);
    List<Produce> getProduceWithAverageRating();
    List<Produce> getProduceBySellerId(Long sellerId);
//    List<Produce> getProduceWithSoldCount();

    Produce updateProduceStatus(Long productId, boolean isActive);
}
