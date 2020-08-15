package com.juanarroyes.reactiveapirest.controller;

import com.juanarroyes.reactiveapirest.dto.BrandDTO;
import com.juanarroyes.reactiveapirest.entity.Brand;
import com.juanarroyes.reactiveapirest.service.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("{id}")
    public Mono<Brand> getBrandById(@PathVariable UUID id) {
        return brandService.getBrandById(id);
    }

    @GetMapping
    public Flux<Brand> getBrandList() {
        return brandService.getBrandList();
    }

    @PostMapping
    public Mono<Brand> createBrand(@RequestBody BrandDTO brandDTO) {
        UUID id = UUID.randomUUID();
        brandDTO.setId(id);
        return brandService.createUniqueBrandFromDTO(brandDTO);
    }
}
