package com.sthumbh.service;

import com.sthumbh.Entity.EmployeeEntity;
import com.sthumbh.dto.EmployeeRequest;
import com.sthumbh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeRequest> getEmployee(String empId, String name) {
        List<EmployeeRequest> employeeRequestList = getEmp().stream()
                .filter(i -> i.getEmpId().equals(empId))
                .collect(Collectors.toList());

        if (employeeRequestList.isEmpty()) {
            employeeRequestList = getEmp().stream()
                    .filter(i -> i.getName().equals(name))
                    .collect(Collectors.toList());
        }
        return employeeRequestList;
    }

    private List<EmployeeRequest> getEmp() {
        return Arrays.asList(
                new EmployeeRequest("Aman", "21-10,1991", "Mumbai", "EMP001", 10.00),
                new EmployeeRequest("Rahul", "21-10,1995", "Mumbai", "EMP002", 10.00),
                new EmployeeRequest("Amit", "21-10,1990", "Mumbai", "EMP003", 10.00),
                new EmployeeRequest("Rakesh", "21-10,1997", "Mumbai", "EMP004", 10.00));
    }

    public EmployeeRequest createEmployee(EmployeeRequest employeeRequest) {
        String uuid = String.valueOf(UUID.randomUUID());
        String PAN = getPan();
        EmployeeEntity employee = new EmployeeEntity(uuid,
                employeeRequest.getName(),
                employeeRequest.getDob(), employeeRequest.getAddress(), employeeRequest.getAddress(), PAN, "988765343");

        employeeRepository.save(employee);
        employeeRequest.setEmpId(uuid);
        return employeeRequest;

    }

    private String getPan() {
        return "HGDGDH1202";
    }
}
