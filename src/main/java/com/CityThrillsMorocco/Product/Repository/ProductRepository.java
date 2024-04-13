package com.CityThrillsMorocco.Product.Repository;

import com.CityThrillsMorocco.Product.Model.Product;
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
                    "WHERE u.code = ?1"
    )
    Boolean selectExistsCode(String code);
    Product findByCode(String code);
}