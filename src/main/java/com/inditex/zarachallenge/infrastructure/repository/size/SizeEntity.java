package com.inditex.zarachallenge.infrastructure.repository.size;

import java.time.LocalDateTime;

import com.inditex.zarachallenge.infrastructure.repository.product.ProductEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SIZE")
public class SizeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", nullable = false)
  private Long id;

  @Column(name = "SIZE")
  private String size;

  @Column(name = "AVAILABILITY")
  private boolean availability;

  @Column(name = "LAST_UPDATED")
  private LocalDateTime lastUpdated;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRODUCT_ID")
  private ProductEntity product;

}
