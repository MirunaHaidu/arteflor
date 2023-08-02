package com.demo.arteflor.service.ornament;

import com.demo.arteflor.dto.ornament.OrnamentDto;
import com.demo.arteflor.model.ornament.Dimension;
import com.demo.arteflor.model.ornament.Ornament;

import java.util.List;
import java.util.Optional;

public interface OrnamentService {
    Ornament addOrnament(OrnamentDto ornamentDto);
    Ornament getById(Integer id);
    List<OrnamentDto> getAllOrnaments();
    Optional<List<OrnamentDto>> findByName(String name);
    List<OrnamentDto> findByCategoryTitle(String categoryTitle);
    List<OrnamentDto> findByTypeTitle(String type);
    List<OrnamentDto> findByDimension(Dimension dimension);
    Ornament updateOrnament(Integer ornamentId, OrnamentDto ornamentDto);
    String deleteOrnament(Integer ornamentId);
}
