package com.cityshop.cityshopbackend.product;

import com.cityshop.cityshopbackend.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByShop(Shop shopById);

    Product findById(long id);

    List<Product> findByShopIdAndCategoryIgnoreCase(Long shopId, String category);

    @Query("""
        SELECT p
        FROM Product p
        WHERE p.shop.id = :shopId
          AND LOWER(p.name) LIKE LOWER(CONCAT(:query, '%'))
    """)
    List<Product> searchByNamePrefix(
            @Param("shopId") Long shopId,
            @Param("query") String query
    );
}
