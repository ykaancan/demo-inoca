package com.kaancan.demoinoca.controller;

import com.kaancan.demoinoca.entity.Product;
import com.kaancan.demoinoca.entity.request.product.CreateProductRequest;
import com.kaancan.demoinoca.entity.request.product.UpdateProductRequest;
import com.kaancan.demoinoca.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getById(@PathVariable("productId") UUID productId) {
        Product product = productService.getByProductId(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<HttpStatus> updateProduct(@RequestBody UpdateProductRequest updateProductRequest, @PathVariable("productId") UUID productId) {
        productService.updateProduct(updateProductRequest, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("productId") UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }


}
