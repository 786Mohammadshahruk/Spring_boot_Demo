package com.sthumbh.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "name should not be null or blank")
    private String name;

    private String lastName;

    private String address;

    private String mobileNumber;


}
