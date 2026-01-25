package com.cityshop.cityshopbackend.city.controller;

import com.cityshop.cityshopbackend.city.City;
import com.cityshop.cityshopbackend.city.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/cityshop/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // get all cities
    @GetMapping("")
    public List<City> getCities() {
        return cityService.getAllCities();
    }
}
