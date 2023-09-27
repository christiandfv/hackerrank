package com.inditex.zarachallenge.domain.service;

import com.inditex.zarachallenge.domain.entity.SimilarProduct;

public interface SimilarProductService {
    
    SimilarProduct findSimilarProductsById(String productId );
}
