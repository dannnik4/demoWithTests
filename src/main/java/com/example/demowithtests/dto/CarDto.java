package com.example.demowithtests.dto;

import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

@Accessors(chain = true)
public class CarDto {
    public Long id;

    public Boolean carHasActive = Boolean.TRUE;

    public String brand;

    public String model;

    public Date date = Date.from(Instant.now());
}