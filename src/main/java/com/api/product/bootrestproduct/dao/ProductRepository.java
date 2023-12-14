package com.api.product.bootrestproduct.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.api.product.bootrestproduct.entities.Product;

@Component
public interface ProductRepository extends CrudRepository<Product, Integer> {
    public Product findByProductId(int productId);

    @Query(value = "from Product as p where p.productName=:n")
    public Product findByProductName(@Param("n") String productName);

    @Query(value = "from Product as p where p.productPrice>:p")
    public List<Product> findByPriceGreaterThan(@Param("p") double price);
}
