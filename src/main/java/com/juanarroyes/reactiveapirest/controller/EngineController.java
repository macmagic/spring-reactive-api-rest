package com.juanarroyes.reactiveapirest.controller;

import com.juanarroyes.reactiveapirest.dto.EngineDTO;
import com.juanarroyes.reactiveapirest.entity.Engine;
import com.juanarroyes.reactiveapirest.service.EngineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/engine")
public class EngineController {

    private final EngineService engineService;

    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Engine> createEngine(@RequestBody EngineDTO engineDTO) {
        return engineService.createEngine(engineDTO);
    }

    @GetMapping("{id}")
    public Mono<Engine> getEngine(@PathVariable UUID id) {
        return engineService.getEngineById(id);
    }
}
