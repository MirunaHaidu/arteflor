package com.demo.arteflor.repository;

import com.demo.arteflor.model.Ornament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrnamentRepository extends JpaRepository<Ornament, Integer> {
    Ornament findByName(String name);
//    List<Ornament> findByCategory(String category);
//    List<Ornament> findByType(String type);
    List<Ornament> findByModel(String model);
}
