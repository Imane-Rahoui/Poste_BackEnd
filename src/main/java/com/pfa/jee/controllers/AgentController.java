package com.pfa.jee.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jee.requests.AgentRequest;
import com.pfa.jee.responses.AgentResponse;
import com.pfa.jee.responses.ColieResponse;
import com.pfa.jee.services.AgentService;
import com.pfa.jee.services.ColieService;
import com.pfa.jee.shared.dto.AgentDto;
import com.pfa.jee.shared.dto.ColieDto;

@RestController
@RequestMapping("/agents")
public class AgentController {

	@Autowired
	AgentService agentService;

	@Autowired
	ColieService colieService;

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AgentResponse> StoreAgent(@RequestBody AgentRequest agentRequest) {
		ModelMapper modelMapper = new ModelMapper();

		AgentDto agentDto = modelMapper.map(agentRequest, AgentDto.class);
		AgentDto createAgent = agentService.createAgent(agentDto);

		AgentResponse newAgent = modelMapper.map(createAgent, AgentResponse.class);

		return new ResponseEntity<AgentResponse>(newAgent, HttpStatus.CREATED);

	}

	@GetMapping(path = "/colies", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ColieResponse>> PrintColies(Principal principal) {

		List<ColieDto> colies = colieService.getAllColies(principal.getName());
		Type listType = new TypeToken<List<ColieResponse>>() {
		}.getType();
		List<ColieResponse> coliesResponse = new ModelMapper().map(colies, listType);
		return new ResponseEntity<List<ColieResponse>>(coliesResponse, HttpStatus.OK);

	}

	@PutMapping(path = "/colies/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> DelivredColie(@PathVariable String id) {

		Boolean flag = colieService.delivredColie(id);
		
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);

	}

}
