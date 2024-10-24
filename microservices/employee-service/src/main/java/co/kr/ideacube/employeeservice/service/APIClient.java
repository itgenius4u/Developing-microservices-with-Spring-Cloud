package co.kr.ideacube.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.kr.ideacube.employeeservice.dto.DepartmentDto;

@FeignClient(name = "DEPARTMENT-SERVICE", url = "http://localhost:8080")
public interface APIClient {
    // Build get department rest api
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
