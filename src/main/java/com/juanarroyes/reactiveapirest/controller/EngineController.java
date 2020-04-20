package com.juanarroyes.reactiveapirest.controller;

import com.juanarroyes.reactiveapirest.dto.EngineDTO;
import com.juanarroyes.reactiveapirest.entity.Engine;
import com.juanarroyes.reactiveapirest.service.EngineService;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/api/v1/engines")
public class EngineController {

    private final EngineService engineService;

    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Engine> getEngineById(@PathVariable UUID id) {
        return engineService.getEngineById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Engine> getEngineList() {
        return engineService.getEngineList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Engine> createEngine(@RequestBody EngineDTO engineDTO) {
        UUID uuid = UUID.randomUUID();
        engineDTO.setId(uuid);
        return engineService.createEngine(engineDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Engine> updateEngine(@PathVariable UUID id, @RequestBody EngineDTO engineDTO) {
        return engineService.updateEngine(id, engineDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteEngine(@PathVariable UUID id) {
        return engineService.deleteEngine(id);
    }
}
