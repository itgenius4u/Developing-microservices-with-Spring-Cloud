package co.kr.ideacube.employeeservice.service;

import co.kr.ideacube.employeeservice.dto.APIResponseDto;
import co.kr.ideacube.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
}
