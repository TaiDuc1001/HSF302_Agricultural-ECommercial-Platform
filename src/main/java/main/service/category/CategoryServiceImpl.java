package main.service.category;

import lombok.RequiredArgsConstructor;
import main.dto.CategoryDTO;
import main.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::fromCategory)
                .collect(Collectors.toList());
    }
}
