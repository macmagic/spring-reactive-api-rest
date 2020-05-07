package com.juanarroyes.reactiveapirest.service;

import com.juanarroyes.reactiveapirest.dto.MotorcycleDTO;
import com.juanarroyes.reactiveapirest.entity.Brand;
import com.juanarroyes.reactiveapirest.entity.Motorcycle;
import com.juanarroyes.reactiveapirest.repository.MotorcycleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class MotorcycleServiceTest {

    @Mock
    private MotorcycleRepository repository;

    @Mock
    private BrandService brandService;

    private MotorcycleService motorcycleService;

    @BeforeEach
    public void init() {
        this.motorcycleService = new MotorcycleService(repository, brandService);
    }

    @Test
    public void testGetListMotorcycle() {
        Flux<Motorcycle> expectedMotorcycleList = this.generateMotorcycleList();

        given(repository.findAll()).willReturn(expectedMotorcycleList);
        Flux<Motorcycle> result = this.motorcycleService.getMotorcycleList();
        assertEquals(expectedMotorcycleList, result);
        then(repository).should(times(1)).findAll();
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void testGetMotorcycleById() {
        UUID id = UUID.randomUUID();

        Motorcycle motorcycleExpected = new Motorcycle();
        motorcycleExpected.setId(id);
        motorcycleExpected.setModel("Test model");
        motorcycleExpected.setLicenseType("A");
        motorcycleExpected.setSeat(2);
        motorcycleExpected.setYear(2000);

        given(repository.findById(id)).willReturn(Mono.just(motorcycleExpected));

        Mono<Motorcycle> result = this.motorcycleService.getMotorcycleById(id);

        Motorcycle motorcycleResult = result.block();
        assertEquals(motorcycleExpected, motorcycleResult);
        then(repository).should(times(1)).findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void testCreateMotorcycle() {
        UUID id = UUID.randomUUID();
        MotorcycleDTO motorcycleDTO = new MotorcycleDTO();
        motorcycleDTO.setId(id);
        motorcycleDTO.setBrand("Test");
        motorcycleDTO.setModel("Test model");
        motorcycleDTO.setYear(2000);
        motorcycleDTO.setLicenseType("A");
        motorcycleDTO.setSeat(2);

        Brand brand = Brand.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .createdAt(new Date())
                .build();

        Motorcycle motorcycleExpected = new Motorcycle();
        motorcycleExpected.setId(id);
        motorcycleExpected.setModel(motorcycleDTO.getModel());
        motorcycleExpected.setLicenseType(motorcycleDTO.getLicenseType());
        motorcycleExpected.setSeat(motorcycleDTO.getSeat());
        motorcycleExpected.setYear(motorcycleDTO.getYear());
        motorcycleExpected.setBrand(brand);

        given(brandService.createUniqueBrand(eq(brand.getName()))).willReturn(Mono.just(brand));
        given(repository.save(any(Motorcycle.class))).willReturn(Mono.just(motorcycleExpected));
        Mono<Motorcycle> result = this.motorcycleService.create(motorcycleDTO);
        Motorcycle motorcycleResult = result.block();
        assertNotNull(motorcycleResult);
        assertEquals(motorcycleExpected, motorcycleResult);
        then(brandService).should(times(1)).createUniqueBrand(eq(brand.getName()));
        then(brandService).shouldHaveNoMoreInteractions();
        then(repository).should(times(1)).save(any(Motorcycle.class));
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void testUpdateWithoutBrandMotorcycle() {
        UUID id = UUID.randomUUID();
        MotorcycleDTO motorcycleDTO = new MotorcycleDTO();
        motorcycleDTO.setModel("Test model");
        motorcycleDTO.setLicenseType("A");
        motorcycleDTO.setSeat(2);

        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setId(UUID.randomUUID());
        motorcycle.setModel("Test model");
        motorcycle.setLicenseType("A");
        motorcycle.setSeat(motorcycleDTO.getSeat());
        motorcycle.setYear(motorcycleDTO.getYear());

        given(repository.findById(id)).willReturn(Mono.just(motorcycle));
        given(repository.save(any(Motorcycle.class))).willReturn(Mono.just(motorcycle));
        Mono<Motorcycle> result = this.motorcycleService.update(id, motorcycleDTO);
        Motorcycle motorcycleResult = result.block();
        assertNotNull(motorcycleResult);
        assertEquals(motorcycleDTO.getModel(), motorcycleResult.getModel());
        assertEquals(motorcycleDTO.getLicenseType(), motorcycleResult.getLicenseType());
        then(repository).should(times(1)).findById(id);
        then(repository).should(times(1)).save(any(Motorcycle.class));
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void testUpdateWithBrandMotorcycle() {
        UUID id = UUID.randomUUID();
        MotorcycleDTO motorcycleDTO = new MotorcycleDTO();
        motorcycleDTO.setModel("Test model");
        motorcycleDTO.setBrand("Test brand");
        motorcycleDTO.setLicenseType("A");
        motorcycleDTO.setSeat(2);

        Brand brand = Brand.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .createdAt(new Date())
                .build();

        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setId(UUID.randomUUID());
        motorcycle.setModel("Test model");
        motorcycle.setLicenseType("A");
        motorcycle.setSeat(motorcycleDTO.getSeat());
        motorcycle.setYear(motorcycleDTO.getYear());
        motorcycle.setBrand(brand);

        given(brandService.createUniqueBrand(any(String.class))).willReturn(Mono.just(brand));
        given(repository.findById(id)).willReturn(Mono.just(motorcycle));
        given(repository.save(any(Motorcycle.class))).willReturn(Mono.just(motorcycle));
        Mono<Motorcycle> result = this.motorcycleService.update(id, motorcycleDTO);
        Motorcycle motorcycleResult = result.block();
        assertNotNull(motorcycleResult);
        assertEquals(motorcycleDTO.getModel(), motorcycleResult.getModel());
        assertEquals(motorcycleDTO.getLicenseType(), motorcycleResult.getLicenseType());
        then(brandService).should(times(1)).createUniqueBrand(any(String.class));
        then(brandService).shouldHaveNoMoreInteractions();
        then(repository).should(times(1)).findById(id);
        then(repository).should(times(1)).save(any(Motorcycle.class));
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void testDeleteMotorcycle() {
        UUID id = UUID.randomUUID();

        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setId(id);
        motorcycle.setModel("Test model");
        motorcycle.setLicenseType("A");
        motorcycle.setSeat(2);
        motorcycle.setYear(2000);

        given(repository.findById(id)).willReturn(Mono.just(motorcycle));
        this.motorcycleService.delete(id);
        then(repository).should(times(1)).findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

    private Flux<Motorcycle> generateMotorcycleList() {
        List<Motorcycle> list = new ArrayList<>();
        for(int i = 0; i< 3; i++) {
            Motorcycle motorcycle = new Motorcycle();
            motorcycle.setId(UUID.randomUUID());
            motorcycle.setModel("Test");
            motorcycle.setLicenseType("A");
            motorcycle.setSeat(2);
            motorcycle.setYear(1999);
            list.add(motorcycle);
        }

        return Mono.just(list).flatMapMany(Flux::fromIterable);
    }
}
