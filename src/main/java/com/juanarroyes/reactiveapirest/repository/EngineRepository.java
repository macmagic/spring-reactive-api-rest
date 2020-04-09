package com.juanarroyes.reactiveapirest.repository;

import com.juanarroyes.reactiveapirest.entity.Engine;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface EngineRepository extends ReactiveCrudRepository<Engine, UUID> {
}
