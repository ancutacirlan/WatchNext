package com.example.WatchNext.controller;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.security.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody Category categoryRequest) {
        var category = categoryService.findCategoryByName(categoryRequest.getName())
                .orElse(categoryService.save(categoryRequest));
        return ResponseEntity.ok(category);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        var category = categoryService.findCategoryById(id).map(category1 ->
                ResponseEntity.ok().body(category1)).orElseThrow(EntityNotFoundException::new);
       return category;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

}
