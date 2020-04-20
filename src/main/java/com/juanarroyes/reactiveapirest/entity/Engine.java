package com.juanarroyes.reactiveapirest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Engine {

   @Id
   private UUID id;

   private String model;

   private Integer displacement;

   private String type;

   private String provision;

   private String distribution;

   private Integer numberOfCylinders;

   private Integer valvesPerCylinder;

   private String refrigeration;

   private String powerType;

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
