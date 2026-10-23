package com.codenzic.employee.entity;

import com.codenzic.employee.enums.EmployeeStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String empName;

    @Column(nullable = false)
    private String deptName;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salary;

    private String place;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus status;


    public Employee() {
    }

    public Employee(Long id, String empName, String deptName, BigDecimal salary, String place, String location, EmployeeStatus status) {
        this.id = id;
        this.empName = empName;
        this.deptName = deptName;
        this.salary = salary;
        this.place = place;
        this.location = location;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
}
