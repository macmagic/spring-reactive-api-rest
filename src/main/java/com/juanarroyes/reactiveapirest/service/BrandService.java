package com.juanarroyes.reactiveapirest.service;

import com.juanarroyes.reactiveapirest.entity.Brand;
import com.juanarroyes.reactiveapirest.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class BrandService {

    private BrandRepository brandRepository;

    public Mono<Brand> createUniqueBrand(String brandName) {
        return brandRepository.findBrandByName(brandName)
                .switchIfEmpty(this.createBrand(brandName));
    }

    public Mono<Brand> createBrand(String brandName) {
        UUID uuid = UUID.randomUUID();
        Brand brand = Brand.builder()
                ._id(uuid)
                .name(brandName)
                .createdAt(new Date())
                .build();

        return brandRepository.save(brand);
    }

}
