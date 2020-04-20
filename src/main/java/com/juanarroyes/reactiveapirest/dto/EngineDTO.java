package com.juanarroyes.reactiveapirest.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineDTO {

    private UUID id;

    private String model;

    private String displacement;

    private String type;

    private String provision;

    private String distribution;

    private Integer numberOfCylinders;

    private Integer valvesPerCylinder;

    private String powerType;

    private String refrigeration;

    private Integer cv;

    private Float kw;

    private Integer rpmFullPower;

    private Integer torque;

    private Integer rpmFullTorque;

    private Integer maxRpm;

    private String fuelType;

    private String antipollutionRegulation;

    private String starterType;

    private String primaryTransmission;

    private String secondaryTransmission;

    private String clutch;

    private String clutchActuation;

    private Integer numberOfGears;
}
