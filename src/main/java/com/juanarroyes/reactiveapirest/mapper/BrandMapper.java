package com.juanarroyes.reactiveapirest.mapper;

import com.juanarroyes.reactiveapirest.dto.BrandDTO;
import com.juanarroyes.reactiveapirest.entity.Brand;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDTO toDto(Brand brand);

    @InheritInverseConfiguration(name = "toDto")
    Brand fromDto(BrandDTO brandDTO);

    @InheritInverseConfiguration(name = "toDto")
    Brand updateFromDto(BrandDTO brandDTO, Brand brand);
}
