package com.cityshop.cityshopbackend.city.service;

import com.cityshop.cityshopbackend.city.City;
import com.cityshop.cityshopbackend.city.CityRepository;
import com.cityshop.cityshopbackend.dto.req.city.CityAddDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City getCityByName(String cityName) {
        return cityRepository.getCityByCityName(cityName).orElseThrow();
    }

    public boolean addCity(CityAddDto request) {
        try {

            City city = new City();
            city.setCityName(request.getCityName());
            city.setState(request.getStateName());
            city.setActive(request.isActive());

            cityRepository.save(city);
            return true;
        }
        catch (Exception ex) {
            System.out.println("Something went wrong "+ ex.getMessage());
            return false;
        }
    }
}
