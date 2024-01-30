package com.sthumbh.dto;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private String lastName;

    private String address;

    private String mobileNumber;
}
