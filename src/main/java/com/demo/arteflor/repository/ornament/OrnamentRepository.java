package com.demo.arteflor.repository.ornament;

import com.demo.arteflor.model.ornament.Dimension;
import com.demo.arteflor.model.ornament.Ornament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrnamentRepository extends JpaRepository<Ornament, Integer> {
    Ornament findByName(String name);
    Ornament getById(Integer id);
    List<Ornament> findByCategoryTitle(String categoryTitle);
    List<Ornament> findByTypeTitle(String typeTitle);
    List<Ornament> findByDimension(Dimension dimension);
}
