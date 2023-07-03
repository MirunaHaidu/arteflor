package com.demo.arteflor.convertor.ornament;

import com.demo.arteflor.dto.ornament.CategoryDto;
import com.demo.arteflor.model.ornament.Category;
import com.demo.arteflor.repository.ornament.CategoryRepository;

public class CategoryConvertor {
    private final CategoryRepository categoryRepository;

    public CategoryConvertor(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static Category convertDtoToEntity(CategoryDto categoryDto){
        Category category = new Category();
//        category.setCategoryCode(categoryDto.getCategoryCode());
        category.setTitle(categoryDto.getTitle());

        return category;
    }

    public static CategoryDto convertEntityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
//        categoryDto.setCategoryCode(category.getCategoryCode());
        categoryDto.setTitle(category.getTitle());


        return categoryDto;
    }
}
