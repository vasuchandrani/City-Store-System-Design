package com.cityshop.cityshopbackend.dto.req.city;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityAddDto {

    private final String cityName;
    private final String stateName;
    private final boolean isActive;

    public CityAddDto(String cityName, String stateName, boolean isActive) {
        this.cityName = cityName;
        this.stateName = stateName;
        this.isActive = isActive;
    }
}