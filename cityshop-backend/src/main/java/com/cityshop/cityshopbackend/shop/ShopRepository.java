package com.cityshop.cityshopbackend.shop;

import com.cityshop.cityshopbackend.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {


    List<Shop> findShopByCity(City city);

    Shop findShopByName(String shopName);

    Shop findShopById(Long shopId);

    @Query("""
        SELECT s
        FROM Shop s
        WHERE LOWER(s.name) LIKE LOWER(CONCAT(:query, '%'))
    """)
    List<Shop> searchByNamePrefix(@Param("query") String query);

    List<Shop> findByCategoryIgnoreCase(String category);
}
