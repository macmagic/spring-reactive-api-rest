package com.juanarroyes.reactiveapirest.service;

import com.juanarroyes.reactiveapirest.dto.MotorcycleDTO;
import com.juanarroyes.reactiveapirest.entity.Brand;
import com.juanarroyes.reactiveapirest.entity.Motorcycle;
import com.juanarroyes.reactiveapirest.exception.DataNotFoundException;
import com.juanarroyes.reactiveapirest.mapper.MotorcycleMapper;
import com.juanarroyes.reactiveapirest.repository.MotorcycleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MotorcycleService {

    private MotorcycleRepository repository;

    private BrandService brandService;

    public Mono<Motorcycle> getMotorcycleById(UUID uuid) {
        return repository.findById(uuid)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Cannot find the motorcycle by id %s", uuid.toString()))));
    }

    public Flux<Motorcycle> getMotorcycleList() {
        return repository.findAll();
    }

    public Mono<Motorcycle> create(MotorcycleDTO motorcycleDTO) {
        return brandService.createUniqueBrand(motorcycleDTO.getBrand())
                .flatMap(brand -> {
                    Motorcycle motorcycle = MotorcycleMapper.createFromDTO(motorcycleDTO);
                    motorcycle.setBrand(brand);
                    return repository.save(motorcycle);
                });
    }

    public Mono<Motorcycle> update(UUID _id, MotorcycleDTO motorcycleDTO) {
        return repository.findById(_id)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Cannot find motorcycle by id: %s", _id.toString()))))
                .flatMap(motorcycle -> brandService.createUniqueBrand(motorcycleDTO.getBrand())
                         .flatMap(brand -> {
                             Motorcycle motorcycleUpdated = MotorcycleMapper.updateFromDTO(motorcycleDTO, motorcycle);
                             motorcycleUpdated.setBrand(brand);
                             return repository.save(motorcycleUpdated);
                         }));
    }

    public Mono<Void> delete(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Cannot find motorcycle by id: %s", id.toString()))))
                .flatMap(repository::delete);
    }

}
