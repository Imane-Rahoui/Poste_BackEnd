package com.pfa.jee.security;


/*public class AuthenticationFilter2 extends UsernamePasswordAuthenticationFilter {
	public final AuthenticationManager authenticationManager;

	public AuthenticationFilter2(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			AgentLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), AgentLoginRequest.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String mail = ((User) auth.getPrincipal()).getUsername(); // wakha mktuba username ra rayrjea liya l email
		
		AgentService AgentService = (AgentService) SpringApplicationContext.getBean("agentServiceImpl"); //premier cara obligatoirement en miniscule
		AgentDto agentDto = AgentService.getAgent(mail); //email wakha mktuba username

		String token = Jwts.builder().setSubject(mail)
				.claim("id", agentDto.getAgentId())
				.claim("firstName", agentDto.getFirstName())
				.claim("lastName", agentDto.getLastName())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("user_id", agentDto.getAgentId());
        res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+ agentDto.getAgentId() + "\"}");

	}
}
*/

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfa.jee.SpringApplicationContext;
import com.pfa.jee.requests.AgentLoginRequest;
import com.pfa.jee.services.AgentService;
import com.pfa.jee.shared.dto.AgentDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter2 extends UsernamePasswordAuthenticationFilter {
	public final AuthenticationManager authenticationManager;

	public AuthenticationFilter2(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			AgentLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), AgentLoginRequest.class);
			System.out.print("k");
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String mail = ((User) auth.getPrincipal()).getUsername(); // wakha mktuba username ra rayrjea liya l email
		
		AgentService AgentService = (AgentService) SpringApplicationContext.getBean("agentServiceImpl"); //premier cara obligatoirement en miniscule
		AgentDto agentDto = AgentService.getAgent(mail); //email wakha mktuba username

		String token = Jwts.builder().setSubject(mail)
				.claim("type","agent")
				.claim("id", agentDto.getAgentId())
				.claim("firstName", agentDto.getFirstName())
				.claim("lastName", agentDto.getLastName())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("user_id", agentDto.getAgentId());
        res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+ agentDto.getAgentId() + "\"}");

	}
}

