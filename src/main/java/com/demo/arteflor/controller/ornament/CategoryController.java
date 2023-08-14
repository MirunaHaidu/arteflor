package com.demo.arteflor.controller.ornament;

import com.demo.arteflor.dto.ornament.CategoryDto;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.ornament.Category;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.service.ornament.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@ControllerAdvice
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/admin/category/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDto));
    }

    @GetMapping("/public/category/getAllCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/admin/category/update")
    public ResponseEntity<Category> updateCategory(@RequestParam Integer categoryId, @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.updateCategory(categoryId, categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/admin/category/delete")
    public ResponseEntity<String> deleteCategory(@RequestParam Integer categoryId) {
        String status = categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }


}
