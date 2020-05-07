package com.juanarroyes.reactiveapirest.mapper;

import com.juanarroyes.reactiveapirest.dto.MotorcycleDTO;
import com.juanarroyes.reactiveapirest.entity.Motorcycle;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MotorcycleMapper {

    MotorcycleMapper INSTANCE = Mappers.getMapper(MotorcycleMapper.class);

    @Mapping(target = "brand", ignore = true)
    MotorcycleDTO toDto(Motorcycle motorcycle);

    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "engine", ignore = true)
    Motorcycle fromDto(MotorcycleDTO motorcycleDTO);

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "engine", ignore = true)
    Motorcycle updateFromDto(MotorcycleDTO motorcycleDTO, @MappingTarget Motorcycle motorcycle);
}
