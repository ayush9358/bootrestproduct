package com.api.product.bootrestproduct.services;

import com.api.product.bootrestproduct.dao.ProductRepository;
import com.api.product.bootrestproduct.entities.Product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // get all products
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> list = (ArrayList<Product>) productRepository.findAll();
        return list;
    }

    // get a single product by id
    public Product getProductById(int id) {
        Product p = null;
        p = productRepository.findByProductId(id);
        return p;
    }

    // add new product
    public Product addProduct(Product pro) {

        Product p = productRepository.save(pro);
        return p;
    }

    // delete product
    public void deleteProduct(int id) {

        productRepository.deleteById(id);
    }

    // update product
    public void updateProduct(Product p, int id) {
        p.setProductId(id);
        productRepository.save(p);
    }

    // get product by name
    public Product getProductByName(String name) {
        Product p = null;
        p = productRepository.findByProductName(name);
        return p;
    }

    // get products by AmountGreaterThan
    public List<Product> findByAmountGreaterThan(double price) {

        List<Product> list;
        list = productRepository.findByPriceGreaterThan(price);
        return list;
    }

}