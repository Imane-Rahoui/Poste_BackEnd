package com.pfa.jee.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lots")
public class LotEntity implements Serializable {

	private static final long serialVersionUID = -8002973982924011123L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String lotId;

	@Column(nullable = false, length = 10, unique = true)
	private String nameLot;

	@Column(nullable = false, length = 50)
	private String street;

	@Column(nullable = false)
	private int maxCapacity;

	@ManyToOne
	@JoinColumn(name = "agency_id")
	private AgencyEntity agencyL;

	@OneToOne(mappedBy="lotE",fetch=FetchType.LAZY)
	private AgentEntity agentE;

	@OneToMany(mappedBy = "lotC", fetch = FetchType.LAZY)
	private List<ColieEntity> colies;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public String getNameLot() {
		return nameLot;
	}

	public void setNameLot(String nameLot) {
		this.nameLot = nameLot;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public AgencyEntity getAgencyL() {
		return agencyL;
	}

	public void setAgencyL(AgencyEntity agencyL) {
		this.agencyL = agencyL;
	}

	public AgentEntity getAgentE() {
		return agentE;
	}

	public void setAgentE(AgentEntity agentE) {
		this.agentE = agentE;
	}

	public List<ColieEntity> getColies() {
		return colies;
	}

	public void setColies(List<ColieEntity> colies) {
		this.colies = colies;
	}

}
