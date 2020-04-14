package com.juanarroyes.reactiveapirest.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@Document
@AllArgsConstructor
public class Engine {

   @Id
   private UUID id;

   private String model;

   private Integer displacement;

   private String enginePowerType;

   private String provision;

   private Integer numberOfCylinders;

   private Integer valvesPerCylinder;

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
