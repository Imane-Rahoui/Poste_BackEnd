package com.pfa.jee.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "agencies")
public class AgencyEntity implements Serializable {

	private static final long serialVersionUID = 6344796833612179159L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String agencyId;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 30)
	private String mobile;

	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword;

	@Column(nullable = true)
	private String emailVerificationToken;

	@Column(nullable = false)
	private Boolean emailVerificationStatus = false;

	@Column(nullable = false, length = 30)
	private String country;

	@Column(nullable = false, length = 50)
	private String city;

	@Column(nullable = false, length = 30)
	private String street;

	@Column(nullable = false, length = 50)
	private String postal;

	@OneToMany(mappedBy = "agencyA", fetch = FetchType.EAGER)
	private List<AgentEntity> agents;

	@OneToMany(mappedBy = "agencyC", fetch = FetchType.LAZY)
	private List<ColieEntity> colies;

	@OneToMany(mappedBy = "agencyL", fetch = FetchType.LAZY) //he doens't accept another eager
	private List<LotEntity> lots;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public List<AgentEntity> getAgents() {
		return agents;
	}

	public void setAgents(List<AgentEntity> agents) {
		this.agents = agents;
	}

	public List<LotEntity> getLots() {
		return lots;
	}

	public void setLots(List<LotEntity> lots) {
		this.lots = lots;
	}

	public List<ColieEntity> getColies() {
		return colies;
	}

	public void setColies(List<ColieEntity> colies) {
		this.colies = colies;
	}

}
