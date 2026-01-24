package com.cityshop.cityshopbackend.admin.service;

import com.cityshop.cityshopbackend.city.service.CityService;
import com.cityshop.cityshopbackend.dto.req.city.CityAddDto;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final CityService cityService;

    public AdminService(CityService cityService) {
        this.cityService = cityService;
    }

    public boolean addCity(CityAddDto request) {
        return cityService.addCity(request);
    }
}
