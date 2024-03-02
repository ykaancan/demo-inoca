package com.kaancan.demoinoca.converter;

import com.kaancan.demoinoca.entity.Product;
import com.kaancan.demoinoca.entity.request.product.UpdateProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductUpdateConverter {

    public void updateProduct(Product product, UpdateProductRequest updateProductRequest) {
        product.setName(updateProductRequest.name());
        product.setPrice(updateProductRequest.price());
        product.setStock(updateProductRequest.stock());
    }
}
