package com.pfa.jee.security;

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
import com.pfa.jee.requests.AgencyLoginRequest;
import com.pfa.jee.services.AgencyService;
import com.pfa.jee.shared.dto.AgencyDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			AgencyLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), AgencyLoginRequest.class);

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
		
		AgencyService AgencyService = (AgencyService) SpringApplicationContext.getBean("agencyServiceImpl"); //premier cara obligatoirement en miniscule
		AgencyDto agencyDto = AgencyService.getAgency(mail); //email wakha mktuba username

		String token = Jwts.builder().setSubject(mail)
				.claim("type","agence")
				.claim("id", agencyDto.getAgencyId())
				.claim("name", agencyDto.getName())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("user_id", agencyDto.getAgencyId());
        res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+ agencyDto.getAgencyId() + "\"}");

	}
}

