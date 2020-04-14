package com.juanarroyes.reactiveapirest.mapper;

import com.juanarroyes.reactiveapirest.dto.EngineDTO;
import com.juanarroyes.reactiveapirest.entity.Engine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EngineMapper {

    EngineMapper INSTANCE = Mappers.getMapper(EngineMapper.class);

    Engine fromDTO(EngineDTO engineDTO);
}
