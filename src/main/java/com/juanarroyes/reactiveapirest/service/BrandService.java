package com.juanarroyes.reactiveapirest.service;

import com.juanarroyes.reactiveapirest.dto.BrandDTO;
import com.juanarroyes.reactiveapirest.entity.Brand;
import com.juanarroyes.reactiveapirest.exception.DataNotFoundException;
import com.juanarroyes.reactiveapirest.exception.DuplicateDataException;
import com.juanarroyes.reactiveapirest.mapper.BrandMapper;
import com.juanarroyes.reactiveapirest.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Mono<Brand> createUniqueBrand(String brandName) {
        return brandRepository.findBrandByName(brandName)
                .switchIfEmpty(Mono.defer(() -> this.createBrand(brandName))).log();
    }

    public Mono<Brand> createBrand(String brandName) {
        UUID id = UUID.randomUUID();
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(brandName);
        brand.setCreatedAt(new Date());
        return brandRepository.save(brand);
    }

   public Mono<Brand> createUniqueBrandFromDTO(BrandDTO brandDTO) {
        return brandRepository.findBrandByName(brandDTO.getName())
                .filterWhen(brand -> {
                    if(brand != null) {
                        
                    }
                })
                /*.flatMap(brand -> {
                            if(brand != null) {
                                return Mono.error(new DuplicateDataException(String.format("Brand %s already exists", brand.getName())));
                            }
                            return Mono.empty();
                        }
                )*/
                .switchIfEmpty(Mono.defer(() -> this.createBrandFromDTO(brandDTO)));
    }

    private Mono<Brand> createBrandFromDTO(BrandDTO brandDTO) {
        Brand brand = BrandMapper.INSTANCE.fromDto(brandDTO);
        return brandRepository.save(brand);
    }


    public Mono<Brand> getBrandById(UUID id) {
        return brandRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new DataNotFoundException(String.format("Cannot find brand by id %s", id.toString())))));
    }

    public Flux<Brand> getBrandList() {
        return brandRepository.findAll();
    }
}
