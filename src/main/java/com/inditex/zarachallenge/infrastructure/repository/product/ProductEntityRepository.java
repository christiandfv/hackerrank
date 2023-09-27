package com.inditex.zarachallenge.infrastructure.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity,Long> {
  
  List<ProductEntity> findByIdIn(List<Long> ids);

}
