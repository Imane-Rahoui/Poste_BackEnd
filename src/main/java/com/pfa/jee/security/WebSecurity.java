package com.pfa.jee.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pfa.jee.services.AgencyService;
import com.pfa.jee.services.AgentService;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final AgencyService agencyDetailsService;
	private final AgentService agentDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(AgencyService agencyDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, AgentService agentDetailsService) {
		this.agencyDetailsService = agencyDetailsService;
		this.agentDetailsService = agentDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// pour que deux app vc noms de domaine different puissent communiquer
		http.cors().and() // nactiver l cors ?? on ajoute des en-têtes HTTP afin de permettre à un agent
							// utilisateur d'accéder à des ressources d'un serveur situé sur une autre
							// origine que le site courant.
		.csrf().disable() // ndesactiver csrf hit maendish formulaires f l app hit mutiservices
				.authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL_AGENCY)
				.permitAll().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL_AGENT) 
																								
				// segment users
				.permitAll().anyRequest().authenticated().and()
				.addFilter(getAuthenticationFilter())
				.addFilter(new AuthorizationFilter(authenticationManager()))												
				.addFilter(getAuthenticationFilter2())
				.addFilter(new AuthorizationFilter(authenticationManager()))
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
																			
	}

	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/agencies/login");
		return filter;
	}	
	
	protected AuthenticationFilter2 getAuthenticationFilter2() throws Exception {
		final AuthenticationFilter2 filter = new AuthenticationFilter2(authenticationManager());
		filter.setFilterProcessesUrl("/agents/login");
		return filter;
	}

	// pr creer une instance mn l user li kayna lfok f userDetailsService
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(agencyDetailsService).passwordEncoder(bCryptPasswordEncoder);
		auth.userDetailsService(agentDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}	
}
