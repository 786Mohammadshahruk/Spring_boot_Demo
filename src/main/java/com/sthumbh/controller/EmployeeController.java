package com.sthumbh.controller;

import com.sthumbh.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "X-Requested-With=XMLHttpRequest", params = "id=123")
    //@PostMapping(path = {"/createEmployee", "/updateEmployee",path = {"/createEmployee", "/updateEmployee"}})
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        System.out.println(employee.getName());
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
    //@GetMapping(value = "/getEmployee")
    public Employee getEmployee() {
        return new Employee("hdhd", "jdjhd", "hdhdhd", "jhdhd");
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    //@DeleteMapping(value = "/deleteEmployee")
    public Employee deleteEmployee() {
        return new Employee("hdhd", "jdjhd", "hdhdhd", "jhdhd");
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
    @PutMapping(value = "/updateEmployee")
    public Employee updateEmployee() {
        return new Employee("hdhd", "jdjhd", "hdhdhd", "jhdhd");
    }


}
