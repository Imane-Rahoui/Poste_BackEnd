package com.pfa.jee.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pfa.jee.shared.dto.AgentDto;

public interface AgentService extends UserDetailsService{

	AgentDto createAgent(AgentDto agent);

	AgentDto getAgent(String email);

	List<AgentDto> getNotConfirmedAgents(String email);

	Boolean confirmAgent(String agentId);

	Boolean deleteAgent(String agentId);

	List<AgentDto> getAgents(String email);
}
