package com.demo.arteflor.service;

import com.demo.arteflor.dto.CategoryDto;
import com.demo.arteflor.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category addCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    Optional<CategoryDto> findByCode(Integer code);
    Category findByTitle(String title);
}
