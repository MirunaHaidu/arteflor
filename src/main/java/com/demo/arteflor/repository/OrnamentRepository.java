package com.demo.arteflor.repository;

import com.demo.arteflor.model.Dimension;
import com.demo.arteflor.model.Ornament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrnamentRepository extends JpaRepository<Ornament, Integer> {
    Ornament findByName(String name);
    List<Ornament> findByCategoryTitle(String categoryTitle);
    List<Ornament> findByTypeTitle(String typeTitle);
    List<Ornament> findByDimension(Dimension dimension);
}
