package com.inditex.zarachallenge.controller;

import com.inditex.zarachallenge.domain.entity.SimilarProduct;
import com.inditex.zarachallenge.domain.service.SimilarProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class SimilarController {
    
    private final SimilarProductService productService;
    @GetMapping("/{productId}/similar")
    public ResponseEntity<SimilarProduct> getSimilarProductsById(@PathVariable String productId){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.findSimilarProductsById(productId));
    }
}
