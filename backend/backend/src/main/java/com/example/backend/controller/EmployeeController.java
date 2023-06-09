package com.example.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Employee;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employees")
public class EmployeeController {
    private List<Employee> employees;

    public EmployeeController() {
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        long nextId = employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0L) + 1;

        employee.setId(nextId);
        employees.add(employee);
        
        return employee;
    }


    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee updatedEmployee) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setName(updatedEmployee.getName());
            employee.setName(updatedEmployee.getName());
            employee.setSex(updatedEmployee.getSex());
            employee.setDob(updatedEmployee.getDob());
            employee.setSalary(updatedEmployee.getSalary());
            employee.setDepartment(updatedEmployee.getDepartment());
        }
        return employee;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employees.removeIf(employee -> employee.getId().equals(id));
        reassignIds();
    }

    private void reassignIds() {
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).setId((long) (i + 1));
        }
    }

}