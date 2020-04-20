package com.juanarroyes.reactiveapirest.mapper;

import com.juanarroyes.reactiveapirest.dto.MotorcycleDTO;
import com.juanarroyes.reactiveapirest.entity.Motorcycle;

public class MotorcycleMapper {

    public static Motorcycle createFromDTO(MotorcycleDTO motorcycleDTO) {
        return Motorcycle.builder()
                .id(motorcycleDTO.getUuid())
                .model(motorcycleDTO.getModel())
                .year(motorcycleDTO.getYear())
                .licenseType(motorcycleDTO.getLicenseType())
                .seat(motorcycleDTO.getSeat())
                .build();
    }

    public static Motorcycle updateFromDTO(MotorcycleDTO motorcycleDTO, Motorcycle motorcycle) {
        return motorcycle.toBuilder()
                .model((motorcycleDTO.getModel() != null ) ? motorcycleDTO.getModel() : motorcycle.getModel())
                .year((motorcycleDTO.getYear() != null) ? motorcycleDTO.getYear() : motorcycle.getYear())
                .licenseType((motorcycleDTO.getLicenseType() != null) ? motorcycleDTO.getLicenseType() : motorcycle.getLicenseType())
                .seat((motorcycleDTO.getSeat() != null) ? motorcycleDTO.getSeat() : motorcycle.getSeat())
                .build();
    }
}
