package com.pluralsight.northwind.controllers;

import com.pluralsight.northwind.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    // временный список вместо базы
    private List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Chai", 1, 18.0));
        products.add(new Product(2, "Chang", 1, 19.0));
        products.add(new Product(3, "Aniseed Syrup", 2, 10.0));
        products.add(new Product(4, "Chef Anton's Cajun Seasoning", 2, 22.0));
        return products;
    }

    // ЕДИНСТВЕННЫЙ GET /products (base + bonus)
    @GetMapping("/products")
    public List<Product> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Double maxPrice
    ) {
        List<Product> result = new ArrayList<>();

        for (Product p : getSampleProducts()) {
            boolean matches = true;

            if (name != null && !p.getProductName().toLowerCase().contains(name.toLowerCase())) {
                matches = false;
            }

            if (categoryId != null && p.getCategoryId() != categoryId) {
                matches = false;
            }

            if (maxPrice != null && p.getUnitPrice() > maxPrice) {
                matches = false;
            }

            if (matches) {
                result.add(p);
            }
        }

        return result;
    }

    // GET /products/{id} → конкретный продукт
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        for (Product p : getSampleProducts()) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }
}
