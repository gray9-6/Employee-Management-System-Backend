package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee = EmployeeMapper.employeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.employeeToEmployeeDto(savedEmployee);
    }

    public EmployeeDto getEmployeeById(Long empId){
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new EmployeeNotFound("Employee Not Found By Id:" + empId));
        return EmployeeMapper.employeeToEmployeeDto(employee);
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeList.forEach(employee -> employeeDtos.add(EmployeeMapper.employeeToEmployeeDto(employee)));
        return employeeDtos;
    }

    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFound("Employee Not Found for this Id: "+id));

        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAddress(employeeDto.getAddress());
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.employeeToEmployeeDto(savedEmployee);
    }

    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFound("Employee Not Found for this Id: "+id));
        employeeRepository.delete(employee);
    }
}
