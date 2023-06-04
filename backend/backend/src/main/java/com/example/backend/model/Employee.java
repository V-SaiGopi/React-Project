package com.example.backend.model;

import java.time.LocalDate;

public class Employee {
    private Long id;
    private String name;
    private String sex;
    private LocalDate dob;
    private double salary;
    private String department;

    public Employee() {
    }

    public Employee(Long id, String name, String sex, LocalDate dob, double salary, String department) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.salary = salary;
        this.department = department;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }

}
