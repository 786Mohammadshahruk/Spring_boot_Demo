package com.sthumbh.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {


    @JsonAlias("employee_name")
    @NotBlank(message = "name should not be null or empty")
    @Size(max = 255, message = "name size should not be maximum 255")
    private String name;

    @JsonProperty("employee_dob")
    @NotNull(message = "name should not be null ")
    @Size(max = 10, message = "dob size should not be maximum 10")
    private String dob;

    @Size(max = 100, message = "address size should not be maximum 100")
    private String address;

    private String empId;

    @Digits(integer = 10, fraction = 2, message = "")
    private Double value;


}
