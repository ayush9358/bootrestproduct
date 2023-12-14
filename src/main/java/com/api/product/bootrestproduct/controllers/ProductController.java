package com.api.product.bootrestproduct.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.product.bootrestproduct.entities.Product;
import com.api.product.bootrestproduct.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // get all products handler
    @GetMapping("/products")
    public ResponseEntity<ArrayList<Product>> getAllProducts() {

        ArrayList<Product> list = this.productService.getAllProducts();

        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.of(Optional.of(list));
    }

    // get a single product handler
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {

        Product p = this.productService.getProductById(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.of(Optional.of(p));
    }

    // add a new product handler
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product p) {

        try {
            Product product = this.productService.addProduct(p);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete a product handler
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {

        try {
            this.productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // update a book handler
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product p, @PathVariable("id") int id) {

        try {
            this.productService.updateProduct(p, id);
            return ResponseEntity.of(Optional.of(p));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get product by name handler
    @GetMapping("/products/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable("name") String name) {

        try {
            Product pro = this.productService.getProductByName(name);
            return ResponseEntity.of(Optional.of(pro));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // get products by AmountGreaterThan
    @GetMapping("/products/amount/{amount}")
    public ResponseEntity<List<Product>> getProductByPriceGreaterThan(@PathVariable("amount") double amount) {

        try {
            List<Product> list = productService.findByAmountGreaterThan(amount);
            return ResponseEntity.of(Optional.of(list));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
