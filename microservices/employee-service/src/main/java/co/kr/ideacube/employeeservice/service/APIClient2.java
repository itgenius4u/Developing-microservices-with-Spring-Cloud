package co.kr.ideacube.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.kr.ideacube.employeeservice.dto.OrganizationDto;

@FeignClient(name = "ORGANIZATION-SERVICE", url = "http://localhost:8083")
public interface APIClient2 {
    // Build get department rest api
    @GetMapping("api/organizations/{organization-code}")
    OrganizationDto getOrganization(@PathVariable("organization-code") String organizationCode);
}
