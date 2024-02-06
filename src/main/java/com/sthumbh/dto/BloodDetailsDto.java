package com.sthumbh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BloodDetailsDto {

    @NotBlank(message = "bloodType should not be null or empty. ")
    private String bloodType;

    @NotNull(message = "donateBloodTo should not be null. ")
    private List<String> donateBloodTo;

    @NotNull(message = "receiveBloodFrom should not be null. ")
    private List<String> receiveBloodFrom;

}
