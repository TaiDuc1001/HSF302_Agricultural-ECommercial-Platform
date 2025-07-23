package main.service.category;


import main.dto.CategoryDTO;
import main.pojo.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    List<CategoryDTO> getAllCategories();


}
