package co.kr.ideacube.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.kr.ideacube.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
