package com.pfa.jee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "colies")
public class ColieEntity implements Serializable {

	private static final long serialVersionUID = 4747167807152166210L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false,unique=true)
	private String colieId;

	@Column(nullable = false, length = 10)
	private String cinSender;

	@Column(nullable = false, length = 50)
	private String firstNameSender;

	@Column(nullable = false, length = 50)
	private String lastNameSender;

	@Column(nullable = false, length = 30)
	private String streetSender;

	@Column(nullable = false, length = 20)
	private String mobileSender;

	@Column(nullable = false, length = 120)
	private String emailSender;

	@Column(nullable = false, length = 10)
	private String cinRecipient;

	@Column(nullable = false, length = 50)
	private String firstNameRecipient;

	@Column(nullable = false, length = 50)
	private String lastNameRecipient;

	@Column(nullable = false, length = 30)
	private String countryRecipient;

	@Column(nullable = false, length = 50)
	private String cityRecipient;

	@Column(nullable = false, length = 30)
	private String streetRecipient;

	@Column(nullable = false, length = 20)
	private String mobileRecipient;

	@Column(nullable = false, length = 120)
	private String emailRecipient;

	@Column(nullable = true)
	private Boolean delivred;

	@ManyToOne
	@JoinColumn(name = "agency_id")
	private AgencyEntity agencyC;

	@ManyToOne
	@JoinColumn(name = "lot_id")
	private LotEntity lotC;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColieId() {
		return colieId;
	}

	public void setColieId(String colieId) {
		this.colieId = colieId;
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

	public String getCinRecipient() {
		return cinRecipient;
	}

	public void setCinRecipient(String cinRecipient) {
		this.cinRecipient = cinRecipient;
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

	public AgencyEntity getAgencyC() {
		return agencyC;
	}

	public void setAgencyC(AgencyEntity agencyC) {
		this.agencyC = agencyC;
	}

	public LotEntity getLotC() {
		return lotC;
	}

	public void setLotC(LotEntity lotC) {
		this.lotC = lotC;
	}

	public Boolean getDelivred() {
		return delivred;
	}

	public void setDelivred(Boolean delivred) {
		this.delivred = delivred;
	}

}
