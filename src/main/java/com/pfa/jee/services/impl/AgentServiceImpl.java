package com.pfa.jee.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfa.jee.entities.AgencyEntity;
import com.pfa.jee.entities.AgentEntity;
import com.pfa.jee.repositories.AgencyRepository;
import com.pfa.jee.repositories.AgentRepository;
import com.pfa.jee.services.AgentService;
import com.pfa.jee.shared.Utils;
import com.pfa.jee.shared.dto.AgencyDto;
import com.pfa.jee.shared.dto.AgentDto;

@Service
public class AgentServiceImpl implements AgentService {
	@Autowired
	AgentRepository agentRepository;

	@Autowired
	Utils util;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public AgentDto createAgent(AgentDto agent) {

		AgencyEntity currentAgency = agencyRepository.findByName(agent.getNameAgency());
		ModelMapper modelMapper = new ModelMapper();
		AgencyDto agencyDto = modelMapper.map(currentAgency, AgencyDto.class);

		agent.setAgentId(util.generateStringId(30));
		agent.setEncryptedPassword(bCryptPasswordEncoder.encode(agent.getPassword()));
		agent.setAgencyA(agencyDto);

		AgentEntity agentEntity = modelMapper.map(agent, AgentEntity.class);

		AgentEntity newAgent = agentRepository.save(agentEntity);

		AgentDto agentDto = modelMapper.map(newAgent, AgentDto.class);

		return agentDto;
	}

	@Override
	public AgentDto getAgent(String email) {
		AgentEntity agentEntity = agentRepository.findByEmail(email);
		if (agentEntity == null)
			throw new UsernameNotFoundException(email);

		ModelMapper modelMapper = new ModelMapper();
		AgentDto agentDto = modelMapper.map(agentEntity, AgentDto.class);

		return agentDto;
	}

	@Override
	public Boolean confirmAgent(String agentId) {
		AgentEntity agentEntity = agentRepository.findByAgentId(agentId);
		if(agentEntity==null)
			throw new RuntimeException("Agent doesn't exist !");
		agentEntity.setConfirmed(true);		
		AgentEntity agentUpdate = agentRepository.save(agentEntity);
		if(agentUpdate!=null)
			return true;
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AgentEntity agentEntity = agentRepository.findByEmail(email);

		if (agentEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(agentEntity.getEmail(), agentEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public List<AgentDto> getNotConfirmedAgents(String email) {

		AgencyEntity agency= agencyRepository.findByEmail(email);
		List<AgentEntity> agents= (List<AgentEntity>) agentRepository.findByAgencyA(agency);
		List<AgentEntity> notCAgents = new ArrayList<>();
		for(AgentEntity agent:agents) {
			if(agent.getConfirmed()==null)
				notCAgents.add(agent);
		}
		Type listType = new TypeToken<List<AgentDto>>() {}.getType();
		List<AgentDto> AgentsDto = new ModelMapper().map(notCAgents, listType);		
		return AgentsDto ;	
	}

	@Override
	public List<AgentDto> getAgents(String email) {
		ModelMapper modelMapper = new ModelMapper();

		AgencyEntity agency= agencyRepository.findByEmail(email);
		List<AgentEntity> agents= (List<AgentEntity>) agentRepository.findByAgencyA(agency);
		List<AgentDto> agentsDto  = new ArrayList<>();
		for(AgentEntity agent:agents) {				
			AgentDto a = modelMapper.map(agent, AgentDto.class);
			if(agent.getLotE()==null) 
				a.setFlag("Aucun");
			else
				a.setFlag(agent.getLotE().getNameLot());
			agentsDto.add(a);
		}
		return agentsDto ;	
	}

	@Override
	public Boolean deleteAgent(String agentId) {
		AgentEntity agentEntity = agentRepository.findByAgentId(agentId);
		if (agentEntity == null)
			throw new UsernameNotFoundException(agentId);
		agentRepository.delete(agentEntity);
		return true;
	}
}
