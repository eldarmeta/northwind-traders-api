package com.pluralsight.northwind.controllers;

import com.pluralsight.northwind.data.ProductDao;
import com.pluralsight.northwind.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductDao productDao;

    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public List<Product> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Double maxPrice
    ) {
        List<Product> products = productDao.getAll();
        List<Product> result = new ArrayList<>();

        for (Product p : products) {
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

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDao.getById(id);
    }
}
