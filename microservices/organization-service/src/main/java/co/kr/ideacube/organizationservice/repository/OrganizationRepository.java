package co.kr.ideacube.organizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.kr.ideacube.organizationservice.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByOrganizationCode(String organizationCode);
}
