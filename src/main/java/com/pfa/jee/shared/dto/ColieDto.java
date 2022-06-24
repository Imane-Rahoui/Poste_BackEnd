package com.pfa.jee.shared.dto;

public class ColieDto {

	private long id;
	private String colieId;
	private String nameLot;
	private String cinSender;
	private String firstNameSender;
	private String lastNameSender;
	private String streetSender;
	private String mobileSender;
	private String emailSender;
	private String cinRecipient;
	private String firstNameRecipient;
	private String lastNameRecipient;
	private String countryRecipient;
	private String cityRecipient;
	private String streetRecipient;
	private String mobileRecipient;
	private String emailRecipient;
	private AgencyDto agencyC;
	private LotDto lotC;
	private Boolean delivred;

	public String getNameLot() {
		return nameLot;
	}
	public void setNameLot(String nameLot) {
		this.nameLot = nameLot;
	}
	public String getCinSender() {
		return cinSender;
	}
	public void setCinSender(String cinSender) {
		this.cinSender = cinSender;
	}
	public String getFirstNameSender() {
		return firstNameSender;
	}
	public void setFirstNameSender(String firstNameSender) {
		this.firstNameSender = firstNameSender;
	}
	public String getLastNameSender() {
		return lastNameSender;
	}
	public void setLastNameSender(String lastNameSender) {
		this.lastNameSender = lastNameSender;
	}
	public String getStreetSender() {
		return streetSender;
	}
	public void setStreetSender(String streetSender) {
		this.streetSender = streetSender;
	}
	public String getMobileSender() {
		return mobileSender;
	}
	public void setMobileSender(String mobileSender) {
		this.mobileSender = mobileSender;
	}
	public String getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}
	public String getFirstNameRecipient() {
		return firstNameRecipient;
	}
	public void setFirstNameRecipient(String firstNameRecipient) {
		this.firstNameRecipient = firstNameRecipient;
	}
	public String getLastNameRecipient() {
		return lastNameRecipient;
	}
	public void setLastNameRecipient(String lastNameRecipient) {
		this.lastNameRecipient = lastNameRecipient;
	}
	public String getCountryRecipient() {
		return countryRecipient;
	}
	public void setCountryRecipient(String countryRecipient) {
		this.countryRecipient = countryRecipient;
	}
	public String getCityRecipient() {
		return cityRecipient;
	}
	public void setCityRecipient(String cityRecipient) {
		this.cityRecipient = cityRecipient;
	}
	public String getStreetRecipient() {
		return streetRecipient;
	}
	public void setStreetRecipient(String streetRecipient) {
		this.streetRecipient = streetRecipient;
	}
	public String getMobileRecipient() {
		return mobileRecipient;
	}
	public void setMobileRecipient(String mobileRecipient) {
		this.mobileRecipient = mobileRecipient;
	}
	public String getEmailRecipient() {
		return emailRecipient;
	}
	public void setEmailRecipient(String emailRecipient) {
		this.emailRecipient = emailRecipient;
	}
	public String getColieId() {
		return colieId;
	}
	public void setColieId(String colieId) {
		this.colieId = colieId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCinRecipient() {
		return cinRecipient;
	}
	public void setCinRecipient(String cinRecipient) {
		this.cinRecipient = cinRecipient;
	}
	public AgencyDto getAgencyC() {
		return agencyC;
	}
	public void setAgencyC(AgencyDto agencyC) {
		this.agencyC = agencyC;
	}
	public LotDto getLotC() {
		return lotC;
	}
	public void setLotC(LotDto lotC) {
		this.lotC = lotC;
	}
	public Boolean getDelivred() {
		return delivred;
	}
	public void setDelivred(Boolean delivred) {
		this.delivred = delivred;
	}

}