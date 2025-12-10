package com.pluralsight.northwind.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(@RequestParam(name = "country", required = false) String country) {

        if (country == null || country.isBlank()) {
            return "Hello World";
        }

        return "Hello " + country;
    }
}
