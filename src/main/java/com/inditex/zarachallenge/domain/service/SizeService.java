package com.inditex.zarachallenge.domain.service;

import com.inditex.zarachallenge.infrastructure.repository.size.SizeEntity;
import org.springframework.stereotype.Service;


public interface SizeService {
    
    SizeEntity findSizeByProductId(Long id);
    
    void updateSize(SizeEntity sizeEntity);
}
