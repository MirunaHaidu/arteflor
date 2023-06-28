package com.demo.arteflor.repository;

import com.demo.arteflor.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
//    Type findByTypeCode(Integer typeCode);
    Type findByTitle(String title);
}
