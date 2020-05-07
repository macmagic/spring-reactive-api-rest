package com.juanarroyes.reactiveapirest.controller;

import com.juanarroyes.reactiveapirest.dto.MotorcycleDTO;
import com.juanarroyes.reactiveapirest.entity.Engine;
import com.juanarroyes.reactiveapirest.entity.Motorcycle;
import com.juanarroyes.reactiveapirest.service.MotorcycleService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/motorcycles", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MotorcycleController {

    private final MotorcycleService motorcycleService;

    public MotorcycleController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping("{id}")
    public Mono<Motorcycle> getMotorcycleById(@PathVariable UUID id) {
        return motorcycleService.getMotorcycleById(id);
    }

    @GetMapping
    public Flux<Motorcycle> getListMotorcycle() {
        return motorcycleService.getMotorcycleList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Motorcycle> create(@RequestBody MotorcycleDTO motorcycleDTO) {
        UUID uuid = UUID.randomUUID();
        motorcycleDTO.setId(uuid);
        return motorcycleService.create(motorcycleDTO);
    }

    @PutMapping("{id}")
    public Mono<Motorcycle> update(
            @PathVariable UUID id,
            @RequestBody MotorcycleDTO motorcycleDTO) {
        return motorcycleService.update(id, motorcycleDTO);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable UUID id) {
        return motorcycleService.delete(id);
    }

    @GetMapping("{id}/engine")
    public Mono<Engine> getEngineByMotorcycleId(@PathVariable UUID id) {
        return motorcycleService.getEngineByMotorcycleId(id);
    }
}
