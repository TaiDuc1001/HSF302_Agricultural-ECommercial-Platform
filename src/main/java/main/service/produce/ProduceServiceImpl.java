package main.service.produce;

import main.dto.ProduceDTO;
import main.dto.ProduceDetailDTO;
import main.mapper.ProduceDetailMapper;
import main.mapper.ProduceMapper;
import main.pojo.Produce;
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
    public List<ProduceDTO> getAllProduce() {
        List<Produce> list = produceRepository.findAll();
        if(list.isEmpty()) {
            throw new IllegalArgumentException("No produce items found");
        }
        return produceMapper.toDTOs(list);
    }


    @Override
    public ProduceDetailDTO getProduceById(Long id) {
        Optional<Produce> produce = produceRepository.findById(id);
        if (produce.isEmpty()) {
            throw new IllegalArgumentException("Produce item not found with id: " + id);
        }
        return produceDetailMapper.toDTO(produce.get());
    }
}
