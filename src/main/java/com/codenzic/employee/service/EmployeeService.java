package com.codenzic.employee.service;

import com.codenzic.employee.dto.EmployeeRequestDto;
import com.codenzic.employee.dto.EmployeeResponseDto;
import com.codenzic.employee.entity.Employee;
import com.codenzic.employee.enums.EmployeeStatus;
import com.codenzic.employee.exception.ResourceNotFoundException;
import com.codenzic.employee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    public EmployeeResponseDto createEmployee(EmployeeRequestDto request) {
        Employee employee = new Employee();
        copyRequestToEntity(request, employee);

        Employee saved = employeeRepository.save(employee);
        return toResponse(saved);
    }



    public EmployeeResponseDto getEmployeeById(Long id) {
        return toResponse(findEmployeeOrThrow(id));
    }




    public Page<EmployeeResponseDto> getAllEmployees(
            int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return employeeRepository.findAll(pageable)
                .map(this::toResponse);
    }

    public List<EmployeeResponseDto> getEmployeesByStatus(EmployeeStatus status) {

        return employeeRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public List<EmployeeResponseDto> searchEmployee(String keyeordd){

        String value = keyeordd.toLowerCase().trim();

        Specification<Employee> specification =
                (root, query, cb) ->
                        cb.or(
                                cb.like(cb.lower(root.get("empName")), "%" + value + "%"),
                                cb.like(cb.lower(root.get("deptName")), "%" + value + "%")
                        );

        return employeeRepository.findAll(specification)
                .stream()
                .map(this::toResponse)
                .toList();



    }




    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request) {
        Employee employee = findEmployeeOrThrow(id);
        copyRequestToEntity(request, employee);

        Employee updated = employeeRepository.save(employee);
        return toResponse(updated);
    }



    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeOrThrow(id);
        employeeRepository.delete(employee);
    }



    private Employee findEmployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }



    private void copyRequestToEntity(EmployeeRequestDto request, Employee employee) {
        employee.setEmpName(request.getEmpName());
        employee.setDeptName(request.getDeptName());
        employee.setSalary(request.getSalary());
        employee.setPlace(request.getPlace());
        employee.setLocation(request.getLocation());
        employee.setStatus(request.getStatus());
    }

    private EmployeeResponseDto toResponse(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(employee.getId());
        dto.setEmpName(employee.getEmpName());
        dto.setDeptName(employee.getDeptName());
        dto.setSalary(employee.getSalary());
        dto.setPlace(employee.getPlace());
        dto.setLocation(employee.getLocation());
        dto.setStatus(employee.getStatus());
        return dto;
    }
}
