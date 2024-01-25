package com.sthumbh.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeEntity {

    private String employeeEmpId;
    private String employeeName;
    private String employeeDob;
    private String employeeOfficeAddress;
    private String employeeHomeAddress;
    private String pan;
    private String contact;

}
