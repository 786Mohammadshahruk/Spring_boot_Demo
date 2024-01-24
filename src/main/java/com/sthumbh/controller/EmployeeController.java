package com.sthumbh.controller;

import com.sthumbh.model.Employee;
import com.sthumbh.service.EmployeeService;
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


    @GetMapping(value = "/getEmp/{emp_id}/{name}")
    public ResponseEntity<List<Employee>> getEmployeeDetails(@PathVariable(name = "emp_id") String empId,
                                                             @PathVariable(name = "name") String name
    ) {
        List<Employee> employeeList = employeeService.getEmployee(empId, name);

        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping(value = "/getEmp")
    public ResponseEntity<List<Employee>> getEmpDetail(@RequestParam(name = "empId", required = false, defaultValue = "EMP001") String empId,
                                                       @RequestParam(name = "name", required = false) String name) {
        List<Employee> employeeList = employeeService.getEmployee(empId, name);

        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


    @GetMapping("/getEmpByHeader")
    public ResponseEntity<List<Employee>> getDetail(@RequestHeader(name = "user-agent") boolean userAgent,
                                                    @RequestHeader(name = "content-type", required = false) String value) {

        if (userAgent && value.equals("text")) {
            List<Employee> employeeList = employeeService.getEmployee("EMP001", "name");

            if (employeeList.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


}
