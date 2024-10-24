package co.kr.ideacube.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import co.kr.ideacube.employeeservice.dto.APIResponseDto;
import co.kr.ideacube.employeeservice.dto.DepartmentDto;
import co.kr.ideacube.employeeservice.dto.EmployeeDto;
import co.kr.ideacube.employeeservice.dto.OrganizationDto;
import co.kr.ideacube.employeeservice.entity.Employee;
import co.kr.ideacube.employeeservice.mapper.EmployeeMapper;
import co.kr.ideacube.employeeservice.repository.EmployeeRepository;
// import org.springframework.web.reactive.function.client.WebClient;
// import co.kr.ideacube.employeeservice.service.APIClient;
// import co.kr.ideacube.employeeservice.service.APIClient2;
import co.kr.ideacube.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;
    // private WebClient webClient;
    // private APIClient apiClient;
    // private APIClient2 apiClient2;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saveDEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(saveDEmployee);
        return savedEmployeeDto;
    }

    @CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId).get();

    //    ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
    //    ResponseEntity<OrganizationDto> responseEntity2 = restTemplate.getForEntity("http://localhost:8083/api/organizations/" + employee.getOrganizationCode(), OrganizationDto.class);

       ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://DEPARTMENT-SERVICE/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
       ResponseEntity<OrganizationDto> responseEntity2 = restTemplate.getForEntity("http://ORGANIZATION-SERVICE/api/organizations/" + employee.getOrganizationCode(), OrganizationDto.class);


       DepartmentDto departmentDto = responseEntity.getBody();
       OrganizationDto organizationDto = responseEntity2.getBody();

        // DepartmentDto departmentDto = webClient.get()
        //         .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
        //         .retrieve()
        //         .bodyToMono(DepartmentDto.class)
        //         .block();
        
        // OrganizationDto organizationDto = webClient.get()
        //         .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
        //         .retrieve()
        //         .bodyToMono(OrganizationDto.class)
        //         .block();

        // DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());    
        // OrganizationDto organizationDto = apiClient2.getOrganization(employee.getOrganizationCode());


        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);
        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        LOGGER.info("inside getDefaultDepartment() method");

        Employee employee = employeeRepository.findById(employeeId).get();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Department Error");
        departmentDto.setDepartmentCode("99");
        departmentDto.setDepartmentDescription("Department Error");
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);   
        
        

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }
}
