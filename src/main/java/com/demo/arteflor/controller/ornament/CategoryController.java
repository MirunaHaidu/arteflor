package com.demo.arteflor.controller.ornament;

import com.demo.arteflor.dto.ornament.CategoryDto;
import com.demo.arteflor.model.ornament.Category;
import com.demo.arteflor.service.ornament.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/category")
@ControllerAdvice
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.addCategory(categoryDto));
    }
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
