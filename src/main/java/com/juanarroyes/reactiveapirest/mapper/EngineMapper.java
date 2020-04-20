package com.juanarroyes.reactiveapirest.mapper;

import com.juanarroyes.reactiveapirest.dto.EngineDTO;
import com.juanarroyes.reactiveapirest.entity.Engine;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EngineMapper {

    EngineMapper INSTANCE = Mappers.getMapper(EngineMapper.class);

    Engine fromDTO(EngineDTO engineDTO);
}
