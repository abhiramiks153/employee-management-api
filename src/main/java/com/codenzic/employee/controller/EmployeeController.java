
package com.codenzic.employee.controller;

import com.codenzic.employee.dto.EmployeeRequestDto;
import com.codenzic.employee.dto.EmployeeResponseDto;
import com.codenzic.employee.enums.EmployeeStatus;
import com.codenzic.employee.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @Valid @RequestBody EmployeeRequestDto request) {

        EmployeeResponseDto created =
                employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponseDto>> searchEmployee(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                employeeService.searchEmployee(keyword)
        );
    }



    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDto>>getAllEmployees(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        return ResponseEntity.ok(
                employeeService.getAllEmployees(pageNumber, pageSize)
        );
    }



    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }



    @GetMapping("/status/{status}")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesByStatus(
            @PathVariable EmployeeStatus status) {

        return ResponseEntity.ok(
                employeeService.getEmployeesByStatus(status)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDto request) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, request)
        );
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}
