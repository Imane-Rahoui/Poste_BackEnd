package com.pfa.jee.responses;

public class LotResponse {

	private String nameLot;
	private String lotId;
	private String street;
	private String maxCapacity;

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

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

}