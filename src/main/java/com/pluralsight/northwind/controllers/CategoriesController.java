package com.pluralsight.northwind.controllers;

import com.pluralsight.northwind.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {

    private List<Category> getSampleCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Beverages"));
        categories.add(new Category(2, "Condiments"));
        categories.add(new Category(3, "Confections"));
        return categories;
    }

    // GET /categories → все категории
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return getSampleCategories();
    }

    // GET /categories/{id} → конкретная категория
    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable int id) {
        for (Category c : getSampleCategories()) {
            if (c.getCategoryId() == id) {
                return c;
            }
        }
        return null;
    }
}
