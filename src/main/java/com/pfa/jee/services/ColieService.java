package com.pfa.jee.services;

import java.util.List;

import com.pfa.jee.shared.dto.ColieDto;

public interface ColieService {

	ColieDto createColie(ColieDto colie,String emailAgency);
	List<ColieDto> getAllColies(String email);
	Boolean delivredColie(String id);
	Boolean deleteColie(String id);
	List<ColieDto> getAllColiesAgency(String email);
	List<ColieDto> getColiesAgency(String email,String cin);
}
