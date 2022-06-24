package com.pfa.jee.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfa.jee.entities.AgencyEntity;
import com.pfa.jee.repositories.AgencyRepository;
import com.pfa.jee.services.AgencyService;
import com.pfa.jee.shared.Utils;
import com.pfa.jee.shared.dto.AgencyDto;

@Service
public class AgencyServiceImpl implements AgencyService {

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	Utils util;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public AgencyDto createAgency(AgencyDto agencyDto) {

		AgencyEntity checkAgency = agencyRepository.findByEmail(agencyDto.getEmail());
		if (checkAgency != null)
			throw new RuntimeException("Agency Already Exists !");

		ModelMapper modelMapper = new ModelMapper();
		AgencyEntity agencyEntity = modelMapper.map(agencyDto, AgencyEntity.class);

		agencyEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(agencyDto.getPassword()));
		agencyEntity.setAgencyId(util.generateStringId(32));

		AgencyEntity newAgency = agencyRepository.save(agencyEntity);

		AgencyDto newAgencyDto = modelMapper.map(newAgency, AgencyDto.class);

		return newAgencyDto;
	}

	@Override
	public AgencyDto getAgency(String email) {
		AgencyEntity agencyEntity = agencyRepository.findByEmail(email);
		if (agencyEntity == null)
			throw new UsernameNotFoundException(email);

		ModelMapper modelMapper = new ModelMapper();
		AgencyDto agencyDto = modelMapper.map(agencyEntity, AgencyDto.class);

		return agencyDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AgencyEntity agencyEntity = agencyRepository.findByEmail(email);

		if (agencyEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(agencyEntity.getEmail(), agencyEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public AgencyDto updateAgency(String id, AgencyDto agencydto) {
		
		AgencyEntity agencyEntity = agencyRepository.findByAgencyId(id);
		if (agencyEntity == null)
			throw new UsernameNotFoundException(id);
		
		agencyEntity.setMobile(agencydto.getMobile());
		agencyEntity.setStreet(agencydto.getStreet());
		agencyEntity.setCity(agencydto.getCity());
		agencyEntity.setCountry(agencydto.getCountry());
		agencyEntity.setPostal(agencydto.getPostal());
		AgencyEntity agencyUpdated = agencyRepository.save(agencyEntity);

		ModelMapper modelMapper = new ModelMapper();
		AgencyDto user = modelMapper.map(agencyUpdated, AgencyDto.class);

		return user;
	}

	@Override
	public List<AgencyDto> getAllAgencies() {
		
		List<AgencyEntity> agencies = (List<AgencyEntity>) agencyRepository.findAll();
		
		Type listType = new TypeToken<List<AgencyDto>>() {}.getType();
		List<AgencyDto> AgenciesDto = new ModelMapper().map(agencies, listType);		
		return AgenciesDto ;	
		
	}

}
