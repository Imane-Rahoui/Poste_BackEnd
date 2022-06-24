package com.pfa.jee.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfa.jee.entities.AgencyEntity;
import com.pfa.jee.entities.AgentEntity;
import com.pfa.jee.repositories.LotRepository;
import com.pfa.jee.entities.ColieEntity;
import com.pfa.jee.entities.LotEntity;
import com.pfa.jee.repositories.AgencyRepository;
import com.pfa.jee.repositories.AgentRepository;
import com.pfa.jee.repositories.ColieRepository;
import com.pfa.jee.services.ColieService;
import com.pfa.jee.services.LotService;
import com.pfa.jee.shared.Utils;
import com.pfa.jee.shared.dto.AgencyDto;
import com.pfa.jee.shared.dto.AgentForLotRequestDto;
import com.pfa.jee.shared.dto.ColieDto;
import com.pfa.jee.shared.dto.LotDto;


@Service
public class LotServiceImpl implements LotService {

	@Autowired
	LotRepository lotRepository;

	@Autowired
	Utils util;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	AgentRepository agentRepository;

	@Override
	public LotDto createLot(LotDto lot, String email) {

		AgencyEntity currentAgency = agencyRepository.findByEmail(email);
		ModelMapper modelMapper = new ModelMapper();
		AgencyDto agencyDto = modelMapper.map(currentAgency, AgencyDto.class);

		lot.setLotId(util.generateStringId(30));
		lot.setAgencyL(agencyDto);
		lot.setMaxCapacity("100");

		LotEntity lotEntity = modelMapper.map(lot, LotEntity.class);

		LotEntity newLot = lotRepository.save(lotEntity);

		LotDto lotDto = modelMapper.map(newLot, LotDto.class);

		return lotDto;
	}

	@Override
	public List<LotDto> getAllLots(String email) {
		AgencyEntity agency = agencyRepository.findByEmail(email);
		ModelMapper modelMapper = new ModelMapper();
		List<LotEntity> lots = (List<LotEntity>) lotRepository.findByAgencyL(agency);

		List<LotDto> lotsDto = new ArrayList<>();

		for (LotEntity lot : lots) {
			LotDto lotDto = modelMapper.map(lot, LotDto.class);
			lotsDto.add(lotDto);
		}
		return lotsDto;
	}

	@Override
	public Boolean affecterAgentLot(String id, AgentForLotRequestDto infoDto) {
		LotEntity lotEntity = lotRepository.findByLotId(id);
		if (lotEntity == null)
			throw new RuntimeException("lot doesn't exist !");

		Pageable pageableRequest = PageRequest.of(0, 10);
		Page<AgentEntity> agentPage;
		agentPage = agentRepository.findAgentByFullName(pageableRequest, infoDto.getFirstName(), infoDto.getLastName());
		List<AgentEntity> agent = agentPage.getContent();
		if (agent.isEmpty())
			throw new RuntimeException("agent doesn't exist !");
		AgentEntity myAgent = agent.get(0);
		myAgent.setLotE(lotEntity);
		AgentEntity agentUpdate = agentRepository.save(myAgent);
		if (agentUpdate != null)
			return true;
		return false;
	}

	@Override
	public Boolean deleteLot(String id) {
		LotEntity lotEntity = lotRepository.findByLotId(id);
		if (lotEntity == null)
			throw new UsernameNotFoundException(id);

		AgentEntity lagent = lotEntity.getAgentE();
		if (lagent != null) {
			agentRepository.delete(lagent);
			lagent.setLotE(null);
			lagent = agentRepository.save(lagent);
		}
		lotRepository.delete(lotEntity);
		return true;
	}
}
