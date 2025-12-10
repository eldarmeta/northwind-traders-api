package com.pluralsight.northwind.controllers;

import com.pluralsight.northwind.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    private List<Product> products = new ArrayList<>();

    public ProductsController() {
        products.add(new Product(1, "Chai", 18.00));
        products.add(new Product(2, "Chang", 19.00));
        products.add(new Product(3, "Aniseed Syrup", 10.00));
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return products;
    }
}
