package main.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import main.pojo.Category;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO {
    Long id;
    String name;
    Boolean isActive;
    public static CategoryDTO fromCategory(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .isActive(category.getIsActive())
                .build();
    }
}
