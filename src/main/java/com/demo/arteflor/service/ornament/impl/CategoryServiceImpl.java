package com.demo.arteflor.service.ornament.impl;

import com.demo.arteflor.convertor.ornament.CategoryConvertor;
import com.demo.arteflor.dto.ornament.CategoryDto;
import com.demo.arteflor.exception.ResourceNotFoundException;
import com.demo.arteflor.model.ornament.Category;
import com.demo.arteflor.model.ornament.Ornament;
import com.demo.arteflor.repository.ornament.CategoryRepository;
import com.demo.arteflor.service.ornament.CategoryService;
import com.demo.arteflor.service.ornament.OrnamentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("test_qualifier_categoryServiceImpl")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final OrnamentService ornamentService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, OrnamentService ornamentService) {
        this.categoryRepository = categoryRepository;
        this.ornamentService = ornamentService;
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
    public Category updateCategory(Integer categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));

        if (categoryDto.getId() != null) {
            category.setId(categoryDto.getId());
        }
        if (categoryDto.getTitle() != null) {
            category.setTitle(categoryDto.getTitle());
        }

        return categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));

        List<Ornament> ornaments = category.getOrnaments();

        ornaments.forEach(ornament -> {
            ornamentService.deleteOrnament(ornament.getId());
        });

        categoryRepository.delete(category);

        return "Category with categoryId: " + categoryId + " deleted successfully !!!";
    }


}
