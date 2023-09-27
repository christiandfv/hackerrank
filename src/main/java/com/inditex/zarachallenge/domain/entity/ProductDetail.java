package com.inditex.zarachallenge.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductDetail {

    private String id;
    
    private String name;
    
    private Double price;
    
    private boolean availability;
    
}
