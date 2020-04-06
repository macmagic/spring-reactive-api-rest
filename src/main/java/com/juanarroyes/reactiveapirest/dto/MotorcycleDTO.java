package com.juanarroyes.reactiveapirest.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MotorcycleDTO {

    private UUID uuid;

    private String brand;

    private String model;

    private Integer engineHp;

    private Integer engineTorque;

    private Integer engineDisplacement;

    private Integer seat;

    private Integer numberOfGears;
}
