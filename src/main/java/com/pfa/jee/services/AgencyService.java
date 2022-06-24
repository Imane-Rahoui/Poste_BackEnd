package com.pfa.jee.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pfa.jee.shared.dto.AgencyDto;

public interface AgencyService extends UserDetailsService {

	AgencyDto createAgency(AgencyDto agencyDto);

	AgencyDto getAgency(String email);

	AgencyDto updateAgency(String id, AgencyDto userdto);
	
	List<AgencyDto> getAllAgencies();

}
