package mapper;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;

public class EmployeeMapper {

    private EmployeeMapper(){

    }

    public static Employee employeeDtoToEmployee(EmployeeDto employeeDto){
        return Employee.builder()
                .id(employeeDto.getId())
                .email(employeeDto.getEmail())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .address(employeeDto.getAddress())
                .build();
    }

    public static EmployeeDto employeeToEmployeeDto(Employee employee){
        return EmployeeDto.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .address(employee.getAddress())
                .build();
    }
}
