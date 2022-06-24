package com.pfa.jee.shared.dto;

import java.io.Serializable;

public class LotDto implements Serializable {

	private static final long serialVersionUID = -5052162869782817084L;

	private long id;
	private String lotId;
	private String nameLot;
	private String nameAgency;
	private String street;
	private String maxCapacity;
	private AgencyDto agencyL;

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

	public String getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public String getNameAgency() {
		return nameAgency;
	}

	public void setNameAgency(String nameAgency) {
		this.nameAgency = nameAgency;
	}

	public AgencyDto getAgencyL() {
		return agencyL;
	}

	public void setAgencyL(AgencyDto agencyL) {
		this.agencyL = agencyL;
	}
}
