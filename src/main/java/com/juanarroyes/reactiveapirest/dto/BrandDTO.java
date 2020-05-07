package com.juanarroyes.reactiveapirest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

    private UUID id;

    private String name;

    private Date createdAt;

    private Date updatedAt;

}
