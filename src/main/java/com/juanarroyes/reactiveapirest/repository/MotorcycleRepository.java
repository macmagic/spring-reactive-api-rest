package com.juanarroyes.reactiveapirest.repository;

import com.juanarroyes.reactiveapirest.entity.Motorcycle;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface MotorcycleRepository extends ReactiveCrudRepository<Motorcycle, UUID> {


}
