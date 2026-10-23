package com.codenzic.employee.repository;

import com.codenzic.employee.entity.Employee;
import com.codenzic.employee.enums.EmployeeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> ,
        JpaSpecificationExecutor<Employee> {


    List<Employee> findByStatus(EmployeeStatus status);
}
