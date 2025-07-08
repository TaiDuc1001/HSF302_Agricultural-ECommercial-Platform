package main.controller;


import jakarta.servlet.http.HttpSession;
import main.dto.ProduceDTO;
import main.dto.ProduceDetailDTO;
import main.service.produce.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProduceController {

    @Autowired
    private ProduceService produceService; // Assuming you have a service to handle produce logic

    @GetMapping
    public List<ProduceDTO> getAllProduce() {
        return produceService.getAllProduce();

    }

    @GetMapping("/{id}")
    public ProduceDetailDTO getProduceDetail(@PathVariable Long id) {
        return produceService.getProduceById(id);
    }

}
