package com.pfa.jee.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pfa.jee.entities.AgencyEntity;
import com.pfa.jee.entities.AgentEntity;
import com.pfa.jee.entities.ColieEntity;
import com.pfa.jee.entities.LotEntity;
@Repository
public interface ColieRepository extends PagingAndSortingRepository<ColieEntity, Long>{

	List<ColieEntity> findByLotC(LotEntity currentLot);
	List<ColieEntity> findByAgencyC(AgencyEntity currentAgency);
	ColieEntity findByColieId(String colieId);
	@Query(value="SELECT * FROM colies c WHERE (c.cin_sender= ?1 OR c.cin_recipient=?1)",nativeQuery=true)
	Page<ColieEntity> findColieByCin(Pageable pageableRequest,String cin);

}
