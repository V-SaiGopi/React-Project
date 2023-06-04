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
        employees = new ArrayList<>();
        employees.add(new Employee(1L, "John Doe", "M", LocalDate.of(1990, 5, 15), 50000, "HR"));
        employees.add(new Employee(2L, "Jane Smith", "F", LocalDate.of(1985, 9, 20), 60000, "Sales"));
        employees.add(new Employee(3L, "Robert Johnson", "M", LocalDate.of(1982, 12, 10), 55000, "HR"));
        employees.add(new Employee(4L, "Emily Brown", "F", LocalDate.of(1992, 8, 25), 52000, "Accounts"));
        employees.add(new Employee(5L, "Michael Wilson", "M", LocalDate.of(1991, 3, 5), 58000, "Sales"));
        employees.add(new Employee(6L, "Emma Davis", "F", LocalDate.of(1989, 6, 18), 59000, "Sales"));
        employees.add(new Employee(7L, "William Taylor", "M", LocalDate.of(1987, 11, 30), 53000, "HR"));
        employees.add(new Employee(8L, "Olivia Clark", "F", LocalDate.of(1993, 4, 12), 54000, "Accounts"));
        employees.add(new Employee(9L, "James Anderson", "M", LocalDate.of(1984, 2, 8), 57000, "HR"));
        employees.add(new Employee(10L, "Sophia Rodriguez", "F", LocalDate.of(1988, 7, 22), 51000, "Sales"));
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