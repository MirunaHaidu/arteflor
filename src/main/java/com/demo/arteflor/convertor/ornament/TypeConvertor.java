package com.demo.arteflor.convertor.ornament;

import com.demo.arteflor.dto.ornament.TypeDto;
import com.demo.arteflor.model.ornament.Type;
import com.demo.arteflor.repository.ornament.TypeRepository;

public class TypeConvertor {
    private final TypeRepository typeRepository;

    public TypeConvertor(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public static Type convertDtoToEntity(TypeDto typeDto){
        Type type = new Type();
//        type.setTypeCode(typeDto.getTypeCode());
        type.setTitle(typeDto.getTitle());

        return type;
    }

    public static TypeDto convertEntityToDto(Type type){
        TypeDto typeDto = new TypeDto();
//        typeDto.setTypeCode(type.getTypeCode());
        typeDto.setTitle(type.getTitle());

        return typeDto;
    }
}
