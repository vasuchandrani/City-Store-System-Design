package com.cityshop.cityshopbackend.admin.controller;

import com.cityshop.cityshopbackend.admin.service.AdminAuthService;
import com.cityshop.cityshopbackend.admin.service.AdminService;
import com.cityshop.cityshopbackend.dto.req.admin.AdminLoginRequestDto;
import com.cityshop.cityshopbackend.dto.req.city.CityAddDto;
import com.cityshop.cityshopbackend.dto.res.admin.AdminLoginResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cityshop/admin")
public class AdminController {

    private final AdminService adminService;
    private final AdminAuthService adminAuthService;

    public AdminController(AdminService adminService, AdminAuthService adminAuthService) {
        this.adminService = adminService;
        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/login")
    public AdminLoginResponseDto login(@RequestBody AdminLoginRequestDto request) {
        return adminAuthService.authenticate(request);
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Admin Dashboard";
    }

    @PostMapping("/city")
    public boolean addCity(@RequestBody CityAddDto cityAddDto) {
        return adminService.addCity(cityAddDto);
    }
}

