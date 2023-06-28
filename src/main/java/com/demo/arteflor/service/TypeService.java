package com.demo.arteflor.service;

import com.demo.arteflor.dto.TypeDto;
import com.demo.arteflor.model.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {
    Type addType(TypeDto typeDto);
    List<TypeDto> getAllTypes();
    Optional<Type> findByTypeCode(Integer typeCode);
    Type findByTitle(String title);
}
