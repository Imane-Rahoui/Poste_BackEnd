package com.pfa.jee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="agents")
public class AgentEntity implements Serializable {

	private static final long serialVersionUID = 5767198332355958018L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String agentId;
	
	@Column(nullable=false, length=10,unique=true)
	private String cin;	

	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=120, unique=true)
	private String email;	

	@Column(nullable=false)
	private String encryptedPassword;
	
	@Column(nullable=true)
	private String emailVerificationToken;
	
	@Column(nullable=true)
	private Boolean confirmed=false;

	@Column(nullable = false, length = 30)
	private String country;

	@Column(nullable = false, length = 50)
	private String city;

	@Column(nullable = false, length = 30)
	private String street;	
	
	@Column(nullable = false, length = 20)
	private String mobile;

	@ManyToOne
	@JoinColumn(name="agency_id")
	private AgencyEntity agencyA;
	
	@OneToOne
	@JoinColumn(name="lot_id")
	private LotEntity lotE;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public AgencyEntity getAgencyA() {
		return agencyA;
	}

	public void setAgencyA(AgencyEntity agencyA) {
		this.agencyA = agencyA;
	}

	public LotEntity getLotE() {
		return lotE;
	}

	public void setLotE(LotEntity lotE) {
		this.lotE = lotE;
	}

}