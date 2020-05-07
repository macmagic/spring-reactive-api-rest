package com.juanarroyes.reactiveapirest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motorcycle {

    @Id
    private UUID id;

    private Brand brand;

    private String model;

    private Integer year;

    private String licenseType;

    private Integer seat;

    private Engine engine;
}
