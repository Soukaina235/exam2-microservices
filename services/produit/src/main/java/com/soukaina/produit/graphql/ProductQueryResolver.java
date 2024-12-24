package com.soukaina.produit.graphql;

import com.soukaina.produit.ProductMapper;
import com.soukaina.produit.ProductResponse;
import com.soukaina.produit.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductQueryResolver implements GraphQLQueryResolver {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductResponse getProductById(Integer id) {
        return productService.findById(id);
    }
}