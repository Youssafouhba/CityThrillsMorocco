package com.CityThrillsMorocco.product.Repository;

import com.CityThrillsMorocco.product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Product u " +
                    "WHERE u.title = ?1"
    )
    Boolean selectExistsTitle(String code);
    Product findByTitle(String code);
}