package com.juanarroyes.reactiveapirest.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MotorcycleDTO {

    private UUID id;

    private String brand;

    private String model;

    private Integer year;

    private String licenseType;

    private Integer seat;
}
