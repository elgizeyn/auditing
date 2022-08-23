package com.demo.controller;

import com.demo.dto.InputRequest;
import com.demo.model.Employee;
import com.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/save")
    public String saveEmployee(@RequestBody InputRequest<Employee> request) {
        return employeeService.saveEmployee(request);
    }

    @PutMapping("/update/{id}/{salary}")
    public String updateEmployee(@PathVariable Long id, @PathVariable Double salary, @RequestBody InputRequest<Employee> request) {
        return employeeService.updateEmployee(id, salary, request);
    }

}
