package com.pfa.jee.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfa.jee.entities.AgencyEntity;
import com.pfa.jee.entities.AgentEntity;

@Repository
public interface AgentRepository extends PagingAndSortingRepository<AgentEntity, Long>{
	
	AgentEntity findByEmail(String email);

	AgentEntity findByAgentId(String agentId);

	AgentEntity findByConfirmed(Boolean flag);

	List<AgentEntity> findByAgencyA(AgencyEntity agency);
	
	@Query(value="SELECT * FROM agents a WHERE (a.first_name= ?1 AND a.last_name=?2)",nativeQuery=true)
	Page<AgentEntity> findAgentByFullName(Pageable pageableRequest,String firstName,String lastName);
}
