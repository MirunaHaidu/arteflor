package com.demo.arteflor.service.impl;

import com.demo.arteflor.convertor.CategoryConvertor;
import com.demo.arteflor.convertor.TypeConvertor;
import com.demo.arteflor.dto.CategoryDto;
import com.demo.arteflor.model.Category;
import com.demo.arteflor.repository.CategoryRepository;
import com.demo.arteflor.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("test_qualifier_categoryServiceImpl")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(CategoryDto categoryDto) {
        return categoryRepository.save(CategoryConvertor.convertDtoToEntity(categoryDto));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryConvertor::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> findByCode(Integer code) {
        return Optional.empty();
    }

    @Override
    public Category findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
