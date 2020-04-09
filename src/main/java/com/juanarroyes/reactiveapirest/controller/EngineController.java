package com.juanarroyes.reactiveapirest.controller;

import com.juanarroyes.reactiveapirest.entity.Engine;
import com.juanarroyes.reactiveapirest.service.EngineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/engine")
public class EngineController {

    private EngineService engineService;

    @GetMapping("{id}")
    public Mono<Engine> getEngine(@PathVariable UUID id) {
        return engineService.getEngineById(id);
    }
}
