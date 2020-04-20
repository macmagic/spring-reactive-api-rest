package com.juanarroyes.reactiveapirest.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Document
public class Brand {

    @Id
    private UUID id;

    private String name;

    private Date createdAt;

    private Date updatedAt;
}
