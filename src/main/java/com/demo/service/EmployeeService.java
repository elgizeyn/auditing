package com.demo.service;

import com.demo.dto.InputRequest;
import com.demo.model.Employee;
import com.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    public String saveEmployee(InputRequest<Employee> inputRequest) {
        String currentUser = inputRequest.getLoggedInUser();
        inputRequest.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());

        Employee employee = inputRequest.getEmployee();
        employee.setCreatedBy(currentUser);
        employeeRepository.save(employee);
        return "saved";
    }

    public String updateEmployee(Long id, Double salary, InputRequest<Employee> request) {
        Employee employee = employeeRepository.findById(id).get();

        if (employee != null) {
            employee.setSalary(salary);
            employee.setLastModifiedBy(request.getLoggedInUser());
            employeeRepository.saveAndFlush(employee);
        } else {
            throw new RuntimeException("not found");
        }
        return "updated";
    }
}
