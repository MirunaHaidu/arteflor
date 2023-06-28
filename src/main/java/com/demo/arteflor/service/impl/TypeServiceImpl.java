package com.demo.arteflor.service.impl;

import com.demo.arteflor.convertor.TypeConvertor;
import com.demo.arteflor.dto.TypeDto;
import com.demo.arteflor.model.Category;
import com.demo.arteflor.model.Type;
import com.demo.arteflor.repository.CategoryRepository;
import com.demo.arteflor.repository.TypeRepository;
import com.demo.arteflor.service.TypeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("test_qualifier_typeServiceImpl")
@Transactional
public class TypeServiceImpl implements TypeService {
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;

    public TypeServiceImpl(CategoryRepository categoryRepository, TypeRepository typeRepository) {
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public Type addType(TypeDto typeDto) {
        Type type = TypeConvertor.convertDtoToEntity(typeDto);
        Category category = categoryRepository.findByTitle(typeDto.getCategoryTitle());
        type.setCategory(category);
        return typeRepository.save(type);
    }

    @Override
    public List<TypeDto> getAllTypes() {
        return typeRepository.findAll().stream()
                .map(TypeConvertor::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Type> findByTypeCode(Integer typeCode) {
        return Optional.empty();
    }

    @Override
    public Type findByTitle(String title) {
        return typeRepository.findByTitle(title);
    }
}
