package com.inditex.zarachallenge.infrastructure.repository.size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeEntityRepository extends JpaRepository<SizeEntity,Long> {
    @Query("SELECT s FROM SizeEntity s WHERE s.product.id = :productId")
    SizeEntity findByProduct(@Param("productId") Long productId);
}
