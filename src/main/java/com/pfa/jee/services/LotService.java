package com.pfa.jee.services;

import java.util.List;

import com.pfa.jee.shared.dto.AgentForLotRequestDto;
import com.pfa.jee.shared.dto.LotDto;

public interface LotService {

	LotDto createLot(LotDto lot,String email);
	List<LotDto> getAllLots(String email);
	Boolean affecterAgentLot(String id,AgentForLotRequestDto infoDto);
	Boolean deleteLot(String id);
}
