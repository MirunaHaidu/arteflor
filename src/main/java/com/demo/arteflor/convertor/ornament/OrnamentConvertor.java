package com.demo.arteflor.convertor.ornament;

import com.demo.arteflor.dto.ornament.OrnamentDto;
import com.demo.arteflor.model.ornament.Ornament;
import com.demo.arteflor.repository.ornament.OrnamentRepository;

public class OrnamentConvertor {
    private final OrnamentRepository ornamentRepository;

    public OrnamentConvertor(OrnamentRepository ornamentRepository) {
        this.ornamentRepository = ornamentRepository;
    }

    public static Ornament convertDtoToEntity(OrnamentDto ornamentDto){
        Ornament ornament = new Ornament();
        ornament.setCode(ornamentDto.getCode());
        ornament.setName(ornamentDto.getName());
        ornament.setImageURL(ornamentDto.getImageURL());
        ornament.setPrice(ornamentDto.getPrice());
        ornament.setQuantity(ornamentDto.getQuantity());
        ornament.setDimension(ornamentDto.getDimension());
        ornament.setColor(ornamentDto.getColor());
        ornament.setSize(ornamentDto.getSize());
        ornament.setWithWire(ornamentDto.getWithWire());
        ornament.setDescription(ornamentDto.getDescription());
        ornament.setIngredients(ornamentDto.getIngredients());

        return ornament;
    }

    public static OrnamentDto convertEntityToDto(Ornament ornament){
        OrnamentDto ornamentDto = new OrnamentDto();
        ornamentDto.setCode(ornament.getCode());
        ornamentDto.setName(ornament.getName());
        ornamentDto.setImageURL(ornament.getImageURL());
        ornamentDto.setPrice(ornament.getPrice());
        ornamentDto.setQuantity(ornament.getQuantity());
        ornamentDto.setDimension(ornament.getDimension());
        ornamentDto.setColor(ornament.getColor());
        ornamentDto.setSize(ornament.getSize());
        ornamentDto.setWithWire(ornament.getWithWire());
        ornamentDto.setDescription(ornament.getDescription());
        ornamentDto.setIngredients(ornament.getIngredients());

        return ornamentDto;
    }
}
