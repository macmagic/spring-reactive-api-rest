package com.juanarroyes.reactiveapirest.mapper;

import com.juanarroyes.reactiveapirest.dto.MotorcycleDTO;
import com.juanarroyes.reactiveapirest.entity.Motorcycle;

public class MotorcycleMapper {

    public static Motorcycle createFromDTO(MotorcycleDTO motorcycleDTO) {
        return Motorcycle.builder()
                .id(motorcycleDTO.getUuid())
                .model(motorcycleDTO.getModel())
                .engineHp(motorcycleDTO.getEngineHp())
                .engineTorque(motorcycleDTO.getEngineTorque())
                .engineDisplacement(motorcycleDTO.getEngineDisplacement())
                .seat(motorcycleDTO.getSeat())
                .numberOfGears(motorcycleDTO.getNumberOfGears())
                .build();
    }
}
