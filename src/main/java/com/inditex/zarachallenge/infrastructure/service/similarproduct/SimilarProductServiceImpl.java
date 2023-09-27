package com.inditex.zarachallenge.infrastructure.service.similarproduct;

import com.inditex.zarachallenge.client.ProductClient;
import com.inditex.zarachallenge.domain.entity.ProductDetail;
import com.inditex.zarachallenge.domain.entity.SimilarProduct;
import com.inditex.zarachallenge.domain.service.SimilarProductService;
import com.inditex.zarachallenge.infrastructure.repository.offer.OfferEntity;
import com.inditex.zarachallenge.infrastructure.repository.product.ProductEntity;
import com.inditex.zarachallenge.infrastructure.repository.product.ProductEntityRepository;

import com.inditex.zarachallenge.infrastructure.repository.size.SizeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimilarProductServiceImpl implements SimilarProductService {

    private final ProductClient productClient;

    private final ProductEntityRepository productRepository;

    @Value("${date}")
    private String date;

    @Override
    @Transactional(readOnly = true)
    public SimilarProduct findSimilarProductsById(String productId) {


        List<Long> similarProductsIds = productClient.findSimilarProductsIds(productId);

        if (CollectionUtils.isEmpty(similarProductsIds)) {
            return buildSimilarProduct(Collections.emptyList());
        }

        List<ProductEntity> productEntities = productRepository.findByIdIn(similarProductsIds);

        if (!CollectionUtils.isEmpty(productEntities)) {
            List<ProductDetail> productDetails = new ArrayList<>();
            productEntities.stream().forEach(productEntity -> {
                ProductDetail product = ProductDetail.builder()
                        .id(Long.toString(productEntity.getId()))
                        .name(productEntity.getName())
                        .price(getPrice(productEntity.getOffers()))
                        .availability(getSize(productEntity.getSizes()))
                        .build();
                productDetails.add(product);
            });
            return buildSimilarProduct(productDetails);
        }
        return buildSimilarProduct(Collections.emptyList());


    }


    private SimilarProduct buildSimilarProduct(List<ProductDetail> productDetails) {
        return SimilarProduct.builder().productDetails(productDetails).build();

    }

    private Double getPrice(List<OfferEntity> offerEntities) {
        if (CollectionUtils.isEmpty(offerEntities)) {
            return null;
        }

        LocalDateTime targetDateTime = getParseLocalDateTime();

        return offerEntities.stream()
                .filter(offerEntity -> {
                    LocalDateTime validFrom = offerEntity.getValidFrom();
                    return validFrom != null && !validFrom.isAfter(targetDateTime);
                })
                .map(OfferEntity::getPrice)
                .findFirst()
                .orElse(null);
    }

    private Boolean getSize(List<SizeEntity> sizeEntities) {
        if (CollectionUtils.isEmpty(sizeEntities)) {
            return null;
        }

        LocalDateTime targetDateTime = getParseLocalDateTime();

        return sizeEntities.stream()
                .filter(sizeEntity -> {
                    LocalDateTime validFrom = sizeEntity.getLastUpdated();
                    return validFrom != null && !validFrom.isAfter(targetDateTime);
                })
                .map(SizeEntity::isAvailability)
                .findFirst()
                .orElse(null);
    }

    private LocalDateTime getParseLocalDateTime() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        return LocalDateTime.parse(date, formatter);
    }
}
