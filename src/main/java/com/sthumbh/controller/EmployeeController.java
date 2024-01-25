package com.sthumbh.controller;

import com.sthumbh.Entity.EmployeeEntity;
import com.sthumbh.dto.EmployeeRequest;
import com.sthumbh.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "X-Requested-With=XMLHttpRequest", params = "id=123")
    //@PostMapping(path = {"/createEmployee", "/updateEmployee",path = {"/createEmployee", "/updateEmployee"}})
    public ResponseEntity<EmployeeRequest> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        EmployeeRequest employeeResponse = employeeService.createEmployee(employeeRequest);
        if (employeeResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
    //@GetMapping(value = "/getEmployee")
    public EmployeeRequest getEmployee() {
        return new EmployeeRequest("hdhd", "jdjhd", "hdhdhd", "jhdhd", 10.00);
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    //@DeleteMapping(value = "/deleteEmployee")
    public EmployeeRequest deleteEmployee() {
        return new EmployeeRequest("hdhd", "jdjhd", "hdhdhd", "jhdhd", 10.00);
    }


    @RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
    @PutMapping(value = "/updateEmployee")
    public EmployeeRequest updateEmployee() {
        return new EmployeeRequest("hdhd", "jdjhd", "hdhdhd", "jhdhd", 10.00);
    }


    @GetMapping(value = "/getEmp/{emp_id}/{name}")
    public ResponseEntity<List<EmployeeRequest>> getEmployeeDetails(@PathVariable(name = "emp_id") String empId,
                                                                    @PathVariable(name = "name") String name
    ) {
        List<EmployeeRequest> employeeRequestList = employeeService.getEmployee(empId, name);

        if (employeeRequestList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeRequestList, HttpStatus.OK);
    }

    @GetMapping(value = "/getEmp")
    public ResponseEntity<List<EmployeeRequest>> getEmpDetail(@RequestParam(name = "empId", required = false, defaultValue = "EMP001") String empId,
                                                              @RequestParam(name = "name", required = false) String name) {
        List<EmployeeRequest> employeeRequestList = employeeService.getEmployee(empId, name);

        if (employeeRequestList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeRequestList, HttpStatus.OK);
    }


    @GetMapping("/getEmpByHeader")
    public ResponseEntity<List<EmployeeRequest>> getDetail(@RequestHeader(name = "user-agent") boolean userAgent,
                                                           @RequestHeader(name = "content-type", required = false) String value) {

        if (userAgent && value.equals("text")) {
            List<EmployeeRequest> employeeRequestList = employeeService.getEmployee("EMP001", "name");

            if (employeeRequestList.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(employeeRequestList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


}
