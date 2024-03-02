package com.kaancan.demoinoca.service;

import com.kaancan.demoinoca.converter.ProductUpdateConverter;
import com.kaancan.demoinoca.entity.Product;
import com.kaancan.demoinoca.entity.request.product.CreateProductRequest;
import com.kaancan.demoinoca.entity.request.product.UpdateProductRequest;
import com.kaancan.demoinoca.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductUpdateConverter productUpdateConverter;

    public ProductService(ProductRepository productRepository, ProductUpdateConverter productUpdateConverter) {
        this.productRepository = productRepository;
        this.productUpdateConverter = productUpdateConverter;
    }

    public Product getByProductId(UUID productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    @Transactional
    public void createProduct(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.name());
        product.setPrice(createProductRequest.price());
        product.setStock(createProductRequest.stock());

        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(UpdateProductRequest updateProductRequest, UUID productId) {
        Product currentProduct = productRepository.findById(productId).orElseThrow(); // Custom exception can be used
        productUpdateConverter.updateProduct(currentProduct, updateProductRequest);
        productRepository.save(currentProduct);

    }

    @Transactional
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

}
