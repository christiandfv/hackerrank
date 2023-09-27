package com.inditex.zarachallenge.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class SimilarProduct {
    
    List<ProductDetail> productDetails;
}
