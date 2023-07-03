package com.demo.arteflor.repository.ornament;

import com.demo.arteflor.model.ornament.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
//    Type findByTypeCode(Integer typeCode);
    Type findByTitle(String title);
}
