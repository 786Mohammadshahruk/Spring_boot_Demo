package com.sthumbh.service;

import com.sthumbh.model.Employee;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    public List<Employee> getEmployee(String empId, String name) {
        List<Employee> employeeList = getEmp().stream()
                .filter(i -> i.getEmpId().equals(empId))
                .collect(Collectors.toList());

        if (employeeList.isEmpty()) {
            employeeList = getEmp().stream()
                    .filter(i -> i.getName().equals(name))
                    .collect(Collectors.toList());
        }
        return employeeList;
    }

    private List<Employee> getEmp() {
        return Arrays.asList(
                new Employee("Aman", "21-10,1991", "Mumbai", "EMP001"),
                new Employee("Rahul", "21-10,1995", "Mumbai", "EMP002"),
                new Employee("Amit", "21-10,1990", "Mumbai", "EMP003"),
                new Employee("Rakesh", "21-10,1997", "Mumbai", "EMP004"));
    }

}
