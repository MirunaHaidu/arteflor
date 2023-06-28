package com.demo.arteflor.service;

import com.demo.arteflor.dto.OrnamentDto;
import com.demo.arteflor.model.Ornament;

import java.util.List;
import java.util.Optional;

public interface OrnamentService {
    Ornament addOrnament(OrnamentDto ornamentDto);
    List<OrnamentDto> getAllOrnaments();
    Optional<List<OrnamentDto>> findByName(String name);
//    List<OrnamentDto> findByCategory(String category);
//    List<OrnamentDto> findByType(String type);
    List<OrnamentDto> findByModel(String model);
}
