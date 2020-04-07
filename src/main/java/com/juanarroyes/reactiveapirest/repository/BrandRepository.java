package com.juanarroyes.reactiveapirest.repository;

import com.juanarroyes.reactiveapirest.entity.Brand;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BrandRepository extends ReactiveCrudRepository<Brand, UUID> {

    Mono<Brand> findBrandByName(String brandName);
}
