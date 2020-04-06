package com.juanarroyes.reactiveapirest.service;

import com.juanarroyes.reactiveapirest.dto.MotorcycleDTO;
import com.juanarroyes.reactiveapirest.entity.Motorcycle;
import com.juanarroyes.reactiveapirest.exception.DataNotFoundException;
import com.juanarroyes.reactiveapirest.mapper.MotorcycleMapper;
import com.juanarroyes.reactiveapirest.repository.MotorcycleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MotorcycleService {

    private MotorcycleRepository repository;

    public Mono<Motorcycle> getMotorcycleById(UUID uuid) {
        return repository.findById(uuid)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Cannot find the motorcycle by id %s", uuid.toString()))));
    }

    public Mono<Motorcycle> create(MotorcycleDTO motorcycleDTO) {
        Motorcycle motorcycle = MotorcycleMapper.createFromDTO(motorcycleDTO);
        return repository.save(motorcycle);
    }

}
