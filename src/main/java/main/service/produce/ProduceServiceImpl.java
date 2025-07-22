package main.service.produce;

import jakarta.persistence.EntityNotFoundException;
import main.dto.ProduceDTO;
import main.dto.ProduceDetailDTO;
import main.mapper.ProduceDetailMapper;
import main.mapper.ProduceMapper;
import main.pojo.Produce;
import main.pojo.Review;
import main.repository.ProduceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduceServiceImpl implements ProduceService{

    @Autowired
    private ProduceRepository produceRepository; // Assuming you have a repository to fetch produce data

    private ProduceMapper produceMapper; // Assuming you have a mapper to convert between Produce and ProduceDTO
    private ProduceDetailMapper produceDetailMapper; // Assuming you have a mapper for detailed produce information
    @Override
    public List<Produce> getAllProduce() {
        return produceRepository.findAll();
    }

    @Override
    public Produce getProduceById(Long id) {
        return produceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produce not found with id: " + id));
    }

    @Override
    public List<Produce> getProduceWithAverageRating() {

        return getAllProduce().stream()
                .peek(produce -> produce.setAverageRating(getAverageRating(produce)))
                .toList();
    }

    @Override
    public List<Produce> getProduceBySellerId(Long sellerId) {
        return produceRepository.findByUser_Id(sellerId);
    }

//    @Override
//    public List<Produce> getProduceWithSoldCount() {
//        return List.of();
//    }
//
//    private Integer getSoldCount(Produce produce) {
//        return produce.getUserItems().stream()
//                .mapToInt(item -> item.getQuantity())
//                .sum();
//    }

    private Double getAverageRating(Produce produce) {
        return produce.getReviews().stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
