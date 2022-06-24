package com.pfa.jee.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jee.requests.AgencyRequest;
import com.pfa.jee.requests.AgentForLotRequest;
import com.pfa.jee.requests.ColieRequest;
import com.pfa.jee.requests.LotRequest;
import com.pfa.jee.responses.AgencyResponse;
import com.pfa.jee.responses.AgentResponse;
import com.pfa.jee.responses.ColieResponse;
import com.pfa.jee.responses.LotResponse;
import com.pfa.jee.services.AgencyService;
import com.pfa.jee.services.AgentService;
import com.pfa.jee.services.ColieService;
import com.pfa.jee.services.LotService;
import com.pfa.jee.shared.dto.AgencyDto;
import com.pfa.jee.shared.dto.AgentDto;
import com.pfa.jee.shared.dto.AgentForLotRequestDto;
import com.pfa.jee.shared.dto.ColieDto;
import com.pfa.jee.shared.dto.LotDto;

@RestController
@RequestMapping("/agencies")
public class AgencyController {

	@Autowired
	AgencyService agencyService;

	@Autowired
	ColieService colieService;

	@Autowired
	AgentService agentService;

	@Autowired
	LotService lotService;

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<String>> getAgencies() {
		List<AgencyDto> agencies = agencyService.getAllAgencies();
		List<String> names = new ArrayList<>();
		for (AgencyDto agencyDto : agencies) {
			names.add(agencyDto.getName());
		}
		return new ResponseEntity<List<String>>(names, HttpStatus.OK);
	}

	@GetMapping(path = "/agency", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AgencyResponse> getAgency(Principal principal) {
		AgencyDto agency = agencyService.getAgency(principal.getName());
		ModelMapper modelMapper = new ModelMapper();
		AgencyResponse agencyDto = modelMapper.map(agency, AgencyResponse.class);

		return new ResponseEntity<AgencyResponse>(agencyDto, HttpStatus.OK);
	}

	@GetMapping(path = "/lots", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<LotResponse>> getLots(Principal principal) {
		List<LotDto> lots = lotService.getAllLots(principal.getName());
		ModelMapper modelMapper = new ModelMapper();
		List<LotResponse> lotsResponse = new ArrayList<>();
		for (LotDto lot : lots) {
			LotResponse lotResponse = modelMapper.map(lot, LotResponse.class);
			lotsResponse.add(lotResponse);
		}
		return new ResponseEntity<List<LotResponse>>(lotsResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/agents", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AgentResponse>> getNotConfirmedAgents(Principal principal) {
		List<AgentDto> agents = agentService.getNotConfirmedAgents(principal.getName());
		ModelMapper modelMapper = new ModelMapper();
		List<AgentResponse> agentsResponse = new ArrayList<>();
		for (AgentDto agent : agents) {
			AgentResponse agentResponse = modelMapper.map(agent, AgentResponse.class);
			agentsResponse.add(agentResponse);
		}
		return new ResponseEntity<List<AgentResponse>>(agentsResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/agents/all", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AgentResponse>> getAgents(Principal principal) {
		List<AgentDto> agents = agentService.getAgents(principal.getName());
		ModelMapper modelMapper = new ModelMapper();
		List<AgentResponse> agentsResponse = new ArrayList<>();
		for (AgentDto agent : agents) {
			AgentResponse agentResponse = modelMapper.map(agent, AgentResponse.class);
			agentsResponse.add(agentResponse);
		}
		return new ResponseEntity<List<AgentResponse>>(agentsResponse, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AgencyResponse> CreateAgency(@RequestBody @Valid AgencyRequest agencyRequest)
			throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		AgencyDto agencyDto = modelMapper.map(agencyRequest, AgencyDto.class);

		AgencyDto createAgency = agencyService.createAgency(agencyDto);

		AgencyResponse agencyResponse = modelMapper.map(createAgency, AgencyResponse.class);

		return new ResponseEntity<AgencyResponse>(agencyResponse, HttpStatus.CREATED);
	}

	@PostMapping(path = "/colie", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ColieResponse> CreateColie(@RequestBody @Valid ColieRequest colieRequest, Principal principal)
			throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		ColieDto colieDto = modelMapper.map(colieRequest, ColieDto.class);

		ColieDto createColie = colieService.createColie(colieDto, principal.getName());

		ColieResponse colieResponse = modelMapper.map(createColie, ColieResponse.class);

		return new ResponseEntity<ColieResponse>(colieResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AgencyResponse> PutAgency(@PathVariable String id, @RequestBody AgencyRequest agencyRequest) {

		ModelMapper modelMapper = new ModelMapper();
		AgencyDto agencyDto = modelMapper.map(agencyRequest, AgencyDto.class);

		// BeanUtils.copyProperties(agencyRequest, agencyDto);
		AgencyDto updateAgency = agencyService.updateAgency(id, agencyDto);

		AgencyResponse agencyResponse = modelMapper.map(updateAgency, AgencyResponse.class);

		return new ResponseEntity<AgencyResponse>(agencyResponse, HttpStatus.ACCEPTED);
	}

	@PutMapping(path = "agent/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> PutAgent(@PathVariable String id) {

		Boolean flag = agentService.confirmAgent(id);

		return new ResponseEntity<Boolean>(flag, HttpStatus.NO_CONTENT);

	}

	@PutMapping(path = "lot/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> PutLot(@PathVariable String id, @RequestBody AgentForLotRequest agentForLotRequest) {
		ModelMapper modelMapper = new ModelMapper();
		AgentForLotRequestDto agent = modelMapper.map(agentForLotRequest, AgentForLotRequestDto.class);

		Boolean flag = lotService.affecterAgentLot(id, agent);

		return new ResponseEntity<Boolean>(flag, HttpStatus.NO_CONTENT);

	}

	@DeleteMapping(path = "/agent/{id}")
	public ResponseEntity<Boolean> deleteAgent(@PathVariable String id) {
		Boolean flag = agentService.deleteAgent(id);
		return new ResponseEntity<Boolean>(flag, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/lot/{id}")
	public ResponseEntity<Boolean> deleteLot(@PathVariable String id) {
		Boolean flag = lotService.deleteLot(id);
		return new ResponseEntity<Boolean>(flag, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/colie/{id}")
	public ResponseEntity<Boolean> deleteColie(@PathVariable String id) {
		Boolean flag = colieService.deleteColie(id);
		return new ResponseEntity<Boolean>(flag, HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/lot", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<LotResponse> CreateLot(@RequestBody @Valid LotRequest lotRequest, Principal principal)
			throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		LotDto lotDto = modelMapper.map(lotRequest, LotDto.class);

		LotDto createLot = lotService.createLot(lotDto, principal.getName());

		LotResponse lotResponse = modelMapper.map(createLot, LotResponse.class);

		return new ResponseEntity<LotResponse>(lotResponse, HttpStatus.CREATED);
	}

	@GetMapping(path = "/colies", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ColieResponse>> PrintColies(Principal principal) {
			List<ColieDto> colies = colieService.getAllColiesAgency(principal.getName());
			Type listType = new TypeToken<List<ColieResponse>>() {
			}.getType();
			List<ColieResponse> coliesResponse = new ModelMapper().map(colies, listType);
			return new ResponseEntity<List<ColieResponse>>(coliesResponse, HttpStatus.OK);

	}
		@GetMapping(path = "/colies/{cin}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ColieResponse>> PrintColiesCin(Principal principal, @PathVariable String cin) {
	
			List<ColieDto> colies = colieService.getColiesAgency(principal.getName(),cin);
			Type listType = new TypeToken<List<ColieResponse>>() {
			}.getType();
			List<ColieResponse> coliesResponse = new ModelMapper().map(colies, listType);
			return new ResponseEntity<List<ColieResponse>>(coliesResponse, HttpStatus.OK);
	}
}
