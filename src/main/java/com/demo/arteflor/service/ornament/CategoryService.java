package com.demo.arteflor.service.ornament;

import com.demo.arteflor.dto.ornament.CategoryDto;
import com.demo.arteflor.model.ornament.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category addCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    Category updateCategory(Integer categoryId, CategoryDto categoryDto);
    String deleteCategory(Integer categoryId);
}
