package com.demo.arteflor.service.impl;

import com.demo.arteflor.convertor.OrnamentConvertor;
import com.demo.arteflor.dto.OrnamentDto;
import com.demo.arteflor.exception.OrnamentException;
import com.demo.arteflor.model.Category;
import com.demo.arteflor.model.Dimension;
import com.demo.arteflor.model.Ornament;
import com.demo.arteflor.model.Type;
import com.demo.arteflor.repository.CategoryRepository;
import com.demo.arteflor.repository.OrnamentRepository;
import com.demo.arteflor.repository.TypeRepository;
import com.demo.arteflor.service.OrnamentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("test_qualifier_ornamentServiceImpl")
@Transactional
public class OrnamentServiceImpl implements OrnamentService {
    private final OrnamentRepository ornamentRepository;
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;

    public OrnamentServiceImpl(OrnamentRepository ornamentRepository, CategoryRepository categoryRepository, TypeRepository typeRepository) {
        this.ornamentRepository = ornamentRepository;
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public Ornament addOrnament(OrnamentDto ornamentDto) {
        Ornament ornament = OrnamentConvertor.convertDtoToEntity(ornamentDto);
        Type type = typeRepository.findByTitle(ornamentDto.getTypeTitle());
        Category category = type.getCategory();
        ornament.setCategory(category);
        ornament.setType(type);
        return ornamentRepository.save(ornament);
    }

    @Override
    public List<OrnamentDto> getAllOrnaments() {
        return this.ornamentRepository.findAll().stream()
                .map(OrnamentConvertor::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<List<OrnamentDto>> findByName(String name) {
       List<OrnamentDto> allOrnaments = new ArrayList<>();
       List<OrnamentDto> foundOrnaments = new ArrayList<>();
       ornamentRepository.findAll().forEach(ornament -> allOrnaments.add(OrnamentConvertor.convertEntityToDto(ornament)));
       allOrnaments.stream()
               .filter(ornament->ornament.getName().toLowerCase().contains(name.toLowerCase()))
               .forEach(foundOrnaments::add);

       return Optional.of(foundOrnaments);
    }

//    @Override
//    public List<OrnamentDto> findByCategory(String categoryTitle) {
//        return null;
//    }

    @Override
    public List<OrnamentDto> findByCategoryTitle(String categoryTitle) {
        return ornamentRepository.findByCategoryTitle(categoryTitle).stream()
                .map(OrnamentConvertor::convertEntityToDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list->{
                            if(list.isEmpty()){
                                throw new OrnamentException("There are no ornaments in this category");
                            }
                            return list;
                        }
                ));
    }

    @Override
    public List<OrnamentDto> findByTypeTitle(String typeTitle) {
        return ornamentRepository.findByTypeTitle(typeTitle).stream()
                .map(OrnamentConvertor::convertEntityToDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list->{
                            if(list.isEmpty()){
                                throw new OrnamentException("There are no ornaments with this type");
                            }
                            return list;
                        }
                ));
    }

    @Override
    public List<OrnamentDto> findByDimension(Dimension dimension) {
        return ornamentRepository.findByDimension(dimension).stream()
                .map(OrnamentConvertor::convertEntityToDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list->{
                            if(list.isEmpty()){
                                throw new OrnamentException("There are no ornaments with this dimension");
                            }
                            return list;
                        }
                ));
    }
}
