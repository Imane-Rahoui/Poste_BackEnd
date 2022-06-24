package com.pfa.jee.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfa.jee.entities.AgencyEntity;
import com.pfa.jee.entities.AgentEntity;
import com.pfa.jee.entities.ColieEntity;
import com.pfa.jee.entities.LotEntity;
import com.pfa.jee.repositories.AgencyRepository;
import com.pfa.jee.repositories.AgentRepository;
import com.pfa.jee.repositories.ColieRepository;
import com.pfa.jee.repositories.LotRepository;
import com.pfa.jee.services.ColieService;
import com.pfa.jee.shared.Utils;
import com.pfa.jee.shared.dto.AgencyDto;
import com.pfa.jee.shared.dto.ColieDto;
import com.pfa.jee.shared.dto.LotDto;


@Service
public class ColieServiceImpl implements ColieService {

	@Autowired
	LotRepository lotRepository;

	@Autowired
	ColieRepository colieRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	AgentRepository agentRepository;

	@Autowired
	Utils util;

	@Override
	public ColieDto createColie(ColieDto colie, String emailAgency) {

		AgencyEntity currentAgency = agencyRepository.findByEmail(emailAgency);
		LotEntity lot = lotRepository.findByNameLot(colie.getNameLot());
		ModelMapper modelMapper = new ModelMapper();
		AgencyDto agencyDto = modelMapper.map(currentAgency, AgencyDto.class);
		int c = lot.getMaxCapacity() - 1;
		lot.setMaxCapacity(c);
		LotDto lotDto = modelMapper.map(lot, LotDto.class);

		colie.setColieId(util.generateStringId(30));
		colie.setAgencyC(agencyDto);
		colie.setLotC(lotDto);

		ColieEntity colieEntity = modelMapper.map(colie, ColieEntity.class);

		LotEntity lotUpdate = lotRepository.save(lot);
		ColieEntity newColie = colieRepository.save(colieEntity);

		ColieDto colieDto = modelMapper.map(newColie, ColieDto.class);

		return colieDto;
	}

	@Override
	public List<ColieDto> getAllColies(String email) {

		AgentEntity currentAgent=agentRepository.findByEmail(email);
		LotEntity lot=currentAgent.getLotE();
		
		if(lot==null) 			throw new RuntimeException("this agent is not responsible for any lot");

		List<ColieEntity> colies = (List<ColieEntity>) colieRepository.findByLotC(lot);
		List<ColieEntity> coliesFinal = new ArrayList<>();
		for (ColieEntity colie : colies) 
		{  
			if(colie.getDelivred()==null) 
				coliesFinal.add(colie);
		}
		Type listType = new TypeToken<List<ColieDto>>() {}.getType();
		List<ColieDto> colieDto = new ModelMapper().map(coliesFinal, listType);		
		return colieDto ;

	}


	@Override
	public List<ColieDto> getAllColiesAgency(String email) {

		AgencyEntity currentAgency=agencyRepository.findByEmail(email);
		
		List<ColieEntity> colies = (List<ColieEntity>) colieRepository.findByAgencyC(currentAgency);

		Type listType = new TypeToken<List<ColieDto>>() {}.getType();
		List<ColieDto> colieDto = new ModelMapper().map(colies, listType);		
		return colieDto ;

	}

	@Override
	public Boolean delivredColie(String id) {
		ColieEntity colieEntity = colieRepository.findByColieId(id);
		if (colieEntity == null)
			throw new RuntimeException("Colie doesn't exist !");
		colieEntity.setDelivred(true);
		int c = colieEntity.getLotC().getMaxCapacity() + 1;
		colieEntity.getLotC().setMaxCapacity(c);
		ColieEntity colieUpdated = colieRepository.save(colieEntity);
		LotEntity lot = lotRepository.save(colieEntity.getLotC());
		if (colieUpdated != null || lot != null)
			return true;
		return false;
	}

	@Override
	public Boolean deleteColie(String id) {
		ColieEntity colieEntity = colieRepository.findByColieId(id);
		if (colieEntity == null)
			throw new UsernameNotFoundException(id);
		int c=colieEntity.getLotC().getMaxCapacity()+1;
		colieEntity.getLotC().setMaxCapacity(c);
		lotRepository.save(colieEntity.getLotC());
		colieRepository.delete(colieEntity);
		return true;
	}

	@Override
	public List<ColieDto> getColiesAgency(String email, String cin) {
		AgencyEntity currentAgency=agencyRepository.findByEmail(email);
		
		//List<ColieEntity> colies = (List<ColieEntity>) colieRepository.findByAgencyC(currentAgency);

		Pageable pageableRequest = PageRequest.of(0, 15);	
		Page<ColieEntity> ColiesPage = colieRepository.findColieByCin(pageableRequest,cin);

		List<ColieEntity> colies = ColiesPage.getContent();
		if (colies.isEmpty())
			throw new RuntimeException("colie doesn't exist !");
		List<ColieEntity> coliesFinal = new ArrayList<>();
		for (ColieEntity colie : colies) 
		{  
			if(colie.getAgencyC()==currentAgency) 
				coliesFinal.add(colie);
		}
		Type listType = new TypeToken<List<ColieDto>>() {}.getType();
		List<ColieDto> colieDto = new ModelMapper().map(coliesFinal, listType);		
		return colieDto ;
	}

}
