package com.juanarroyes.reactiveapirest.mapper;

import com.juanarroyes.reactiveapirest.dto.EngineDTO;
import com.juanarroyes.reactiveapirest.entity.Engine;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EngineMapper {

    EngineMapper INSTANCE = Mappers.getMapper(EngineMapper.class);

    EngineDTO toDto(Engine engine);

    @InheritInverseConfiguration(name = "toDto")
    Engine fromDto(EngineDTO engineDTO);

    @InheritInverseConfiguration(name = "toDto")
    Engine updateFromDto(EngineDTO engineDTO, @MappingTarget Engine engine);
}
