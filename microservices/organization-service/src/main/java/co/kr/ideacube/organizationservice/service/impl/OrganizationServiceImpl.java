package co.kr.ideacube.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;

import co.kr.ideacube.organizationservice.dto.OrganizationDto;
import co.kr.ideacube.organizationservice.entity.Organization;
import co.kr.ideacube.organizationservice.mapper.OrganizationMapper;
import co.kr.ideacube.organizationservice.repository.OrganizationRepository;
import co.kr.ideacube.organizationservice.service.OrganizationService;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        // convert OrganizationDto into Organization jpa entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
