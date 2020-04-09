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
        Motorcycle motorcycleExpected = Motorcycle.builder()
                .id(id)
                .model("Test model")
                .year(2000)
                .licenseType("A")
                .seat(2)
                .build();

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
        motorcycleDTO.setUuid(id);
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

        Motorcycle motorcycleExpected = Motorcycle.builder()
                .id(id)
                .model(motorcycleDTO.getModel())
                .licenseType(motorcycleDTO.getLicenseType())
                .year(motorcycleDTO.getYear())
                .seat(motorcycleDTO.getSeat())
                .brand(brand)
                .build();

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

        Motorcycle motorcycle = Motorcycle.builder()
                .id(id)
                .model("Test model")
                .licenseType("A")
                .year(motorcycleDTO.getYear())
                .seat(motorcycleDTO.getSeat())
                .build();

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

        Motorcycle motorcycle = Motorcycle.builder()
                .id(id)
                .model("Test model")
                .licenseType("A")
                .year(motorcycleDTO.getYear())
                .seat(motorcycleDTO.getSeat())
                .brand(brand)
                .build();

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
        Motorcycle motorcycle = Motorcycle.builder()
                .id(id)
                .model("Test model")
                .licenseType("A")
                .year(2000)
                .seat(2)
                .build();
        given(repository.findById(id)).willReturn(Mono.just(motorcycle));
        this.motorcycleService.delete(id);
        then(repository).should(times(1)).findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

    private Flux<Motorcycle> generateMotorcycleList() {
        List<Motorcycle> list = new ArrayList<>();
        for(int i = 0; i< 3; i++) {
            Motorcycle motorcycle = Motorcycle.builder()
                    .id(UUID.randomUUID())
                    .model("Test")
                    .licenseType("A")
                    .seat(2)
                    .year(1999)
                    .build();

            list.add(motorcycle);
        }

        return Mono.just(list).flatMapMany(Flux::fromIterable);
    }
}
