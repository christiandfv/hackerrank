package com.inditex.zarachallenge.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${app.client.url}")
    private String url;


    @Value("${app.client.path}")
    private String path;

    private final RestTemplate restTemplate;
    

    public List<Long> findSimilarProductsIds(String productId) {
        try {
            ResponseEntity<List<Long>> response = restTemplate.exchange(
                    url.concat(productId).concat(path),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (response.getStatusCode() == HttpStatus.OK && !CollectionUtils.isEmpty(response.getBody())) {
                return response.getBody();
            }
            
          
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
           
        } catch (HttpServerErrorException | ResourceAccessException e) {
            log.error(e.getMessage());
          
        }
        return null;
    }
}
