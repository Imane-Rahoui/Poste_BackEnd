package com.pfa.jee.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfa.jee.entities.AgencyEntity;
import com.pfa.jee.entities.LotEntity;

@Repository
public interface LotRepository extends CrudRepository<LotEntity, Long>{
	
	LotEntity findByLotId(String Id);
	LotEntity findByNameLot(String name);
	List<LotEntity> findByAgencyL(AgencyEntity agency);

}
