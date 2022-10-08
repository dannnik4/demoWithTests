package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeSaveDto {

    public Integer id;

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

    @NotNull
    @Schema(description = "Name of the country.", example = "England", required = true)
    public String country;

    @NotNull
    public String address;

    @Email
    @NotNull
    @Schema(description = "Email address of an employee.", example = "billys@mail.com", required = true)
    public String email;

    @NotNull(message = "Phone may not be null")
    public String phone;
}