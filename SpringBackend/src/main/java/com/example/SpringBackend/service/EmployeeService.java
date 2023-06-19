package com.example.SpringBackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBackend.model.Employee;
import com.example.SpringBackend.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setSex(updatedEmployee.getSex());
        existingEmployee.setDob(updatedEmployee.getDob());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setImage(updatedEmployee.getImage());

        return employeeRepository.save(existingEmployee);
    }

    public boolean deleteEmployee(long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(existingEmployee);
        
        // Retrieve the remaining records from the database
        List<Employee> remainingEmployees = employeeRepository.findAll();
        
        // Save the updated records back to the database
        employeeRepository.saveAll(remainingEmployees);
        
        return true;
    }  
}

