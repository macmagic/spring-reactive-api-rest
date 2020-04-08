package com.juanarroyes.reactiveapirest.service;

import com.juanarroyes.reactiveapirest.entity.Brand;
import com.juanarroyes.reactiveapirest.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository repository;

    private BrandService brandService;

    @BeforeEach
    public void init() {
        this.brandService = new BrandService(repository);
    }

    @Test
    public void testCreateBrand() {
        UUID id = UUID.randomUUID();

        Brand brand = Brand.builder()
                ._id(id)
                .name("Test")
                .createdAt(new Date())
                .build();

        given(repository.save(any(Brand.class))).willReturn(Mono.just(brand));
        Mono<Brand> result = this.brandService.createBrand("Test");
        Brand brandResult = result.block();
        assertNotNull(brandResult);
        assertEquals(brand.getName(), brandResult.getName());
        then(repository).should(times(1)).save(any(Brand.class));
    }

    @Test
    public void testCreateUniqueBrandNotExists() {
        UUID id = UUID.randomUUID();

        Brand brand = Brand.builder()
                ._id(id)
                .name("Test")
                .createdAt(new Date())
                .build();

        given(repository.findBrandByName(anyString())).willReturn(Mono.empty());
        given(repository.save(any(Brand.class))).willReturn(Mono.just(brand));
        Mono<Brand> result = this.brandService.createUniqueBrand("Test");
        Brand brandResult = result.block();
        assertNotNull(brandResult);
        assertEquals(brand.getName(), brandResult.getName());
        then(repository).should(times(1)).findBrandByName(anyString());
        then(repository).should(times(1)).save(any(Brand.class));
        then(repository).shouldHaveNoMoreInteractions();
    }


    @Test
    public void testCreateUniqueBrandExistingBrand() {
        UUID id = UUID.randomUUID();

        Brand brand = Brand.builder()
                ._id(id)
                .name("Test")
                .createdAt(new Date())
                .build();

        given(repository.findBrandByName(any())).willReturn(Mono.just(brand));
        Mono<Brand> result = this.brandService.createUniqueBrand("Test");
        Brand brandResult = result.block();
        assertNotNull(brandResult);
        assertEquals(brand.getName(), brandResult.getName());
        then(repository).should(times(1)).findBrandByName(anyString());
        then(repository).shouldHaveNoMoreInteractions();
    }
}
