package com.inditex.zarachallenge.infrastructure.service.size;

import com.inditex.zarachallenge.domain.service.SizeService;
import com.inditex.zarachallenge.infrastructure.repository.size.SizeEntity;
import com.inditex.zarachallenge.infrastructure.repository.size.SizeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl  implements SizeService {
    
    private final SizeEntityRepository sizeEntityRepository;
    
    @Override
    public SizeEntity findSizeByProductId(Long id) {
        
        if(id != null){
           return sizeEntityRepository.findByProduct(id);
        }
        return null;
    }

    @Override
    public void updateSize(SizeEntity sizeEntity) {
        sizeEntityRepository.save(sizeEntity);
    }
}
