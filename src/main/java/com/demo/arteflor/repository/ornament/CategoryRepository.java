package com.demo.arteflor.repository.ornament;

import com.demo.arteflor.model.ornament.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
//    Category findByCategoryCode(Integer categoryCode);
    Category findByTitle(String title);

}
