package com.juanarroyes.reactiveapirest.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
@Builder
public class Motorcycle {

    @Id
    private UUID id;

    private UUID brandId;

    private String model;

    private Integer engineHp;

    private Integer engineTorque;

    private Integer engineDisplacement;

    private Integer seat;

    private Integer numberOfGears;
}
