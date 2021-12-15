package com.revature_team3.backend.web;

import com.revature_team3.backend.entity.Employee;
import com.revature_team3.backend.repository.EmployeeRepository;
import com.revature_team3.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService employeeService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/employees",
            produces = {"application/json","application/xml"}
    )
    public List getAllEmployees() {
        return employeeRepository.findAll();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/employees/{employeeId}",
            produces = {"application/json","application/xml"}
    )
    public ResponseEntity<?> getById(@PathVariable(name = "employeeId") int employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        else {
            return ResponseEntity.ok(optionalEmployee.get());
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/phone-shop/employees",
            consumes = {"application/json","application/xml"}
    )
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        employeeService.register(employee);
        return ResponseEntity.status(201).body(employee);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/phone-shop/employees/{employeeId}"
    )
    public ResponseEntity<?> updateEmployee(
            @PathVariable(name="employeeId") int employeeId,
            @RequestBody Employee employee
    ) {
        employee.setId(employeeId);
        employee= (Employee) employeeRepository.save(employee);
        return ResponseEntity.ok(employee);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/phone-shop/employee/{employeeId}"
    )
    public ResponseEntity<?> deleteEmployee(@PathVariable(name="employeeId") int employeeId) {
        employeeRepository.deleteById(employeeId);
        return ResponseEntity.ok().build();
    }

}
