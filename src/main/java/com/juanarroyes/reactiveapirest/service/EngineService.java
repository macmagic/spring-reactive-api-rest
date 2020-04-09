package com.juanarroyes.reactiveapirest.service;

import com.juanarroyes.reactiveapirest.entity.Engine;
import com.juanarroyes.reactiveapirest.exception.DataNotFoundException;
import com.juanarroyes.reactiveapirest.repository.EngineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EngineService {

    private EngineRepository repository;

    public Mono<Engine> getEngineById(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new DataNotFoundException(String.format("Cannot find the engine by id: %s", id.toString())))));
    }
}
