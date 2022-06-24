package com.pfa.jee.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfa.jee.entities.AgencyEntity;

@Repository
public interface AgencyRepository extends PagingAndSortingRepository<AgencyEntity, Long> {

	AgencyEntity findByEmail(String email);

	AgencyEntity findByAgencyId(String userId);

	AgencyEntity findByName(String nameAgency);

	//AgencyEntity findByName(String name);
}
